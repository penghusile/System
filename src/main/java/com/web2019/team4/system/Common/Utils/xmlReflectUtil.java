package com.web2019.team4.system.Common.Utils;

import java.lang.reflect.Field;
import java.util.*;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

//        Class类	代表类的实体，在运行的Java应用程序中表示类和接口
//        Field类	代表类的成员变量（成员变量也称为类的属性）
//        Method类	代表类的方法
//        Constructor类	代表类的构造方法
//实现读取xml文件中相同类型的bean封装到list中返回
public class xmlReflectUtil {
    private String xmlName;
    private SAXReader reader;
    private Document document;

    public xmlReflectUtil(String xmlName) throws DocumentException {
        this.xmlName = xmlName;
        reader = new SAXReader();
        document = reader.read(this.getClass().getClassLoader().getResourceAsStream(xmlName));
    }

    public <T> List<T> getBeansOfType(Class<T> type) throws Exception {
        List<T> objects = new ArrayList<>();
        Map map = new HashMap<>();
        try {
            Element root = document.getRootElement();
            List<Element> beans = root.elements();
            if (beans.size() > 0) {
                for (Element bean : beans) {
                    if (bean.attributeValue("class").equals(type.getName())) {
                        T object = null;
                        //反射创建对象
                        String class_s = bean.attributeValue("class");
                        Class beanClass = Class.forName(class_s);
                        object = (T) beanClass.newInstance();
                        //对象属性
                        List<Element> propertys = bean.elements();
                        if (propertys.size() > 0) {
                            for (Element property : propertys) {
                                String key = property.attributeValue("name");
                                Field field = beanClass.getDeclaredField(key);
                                field.setAccessible(true);
                                //内嵌的bean
                                List<Element> childBean = property.elements();
                                if (childBean.size() > 0) {
                                    Object childObject = getBean(key, property);
                                    field.set(object, childObject);
                                }
                                if (property.attribute("ref") != null) {
                                    /*
                                     * 此属性的值是一个对象.这里由于直接调用getBean方法赋值给对象,
                                     * 返回的对象一定是Bean参数的对象, 因此强制转换不会出问题
                                     */
                                    String refid = property.attributeValue("ref");
                                    System.out.println("ref:" + refid);
                                    if (map.containsKey(refid)) {
                                        field.set(object, map.get(refid));
                                        System.out.println("success");
                                    }
                                    field.set(object, getBean(refid, root));
                                //  else if (property.attributeValue("name").equals("id")) {
//                                    Integer value = Integer.valueOf(property.attributeValue("value"));
//                                    field.set(object, value);
                                } else {
                                    String value = property.attributeValue("value");
                                    field.set(object, value);
                                }
                            }
                        }
                        objects.add(object);
                        map.put(bean.attributeValue("name"), object);

//                        Set<Map.Entry<String, Object>> entrys = map.entrySet();
//                        int i = 0;
//                        for (Map.Entry<String, Object> item : entrys) {
//                            //通过这种方法也可以达到遍历的效果
//                            System.out.println(map.size());
//                            System.out.println("Map:  " + item.getKey() + ":" + item.getValue());
//                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    //获取property内嵌的bean
    public Object getBean(String name, Element root) throws Exception {
        Object object = null;
        List<Element> beans = root.elements();
        if (beans.size() > 0) {
            for (Element bean : beans) {
                if (bean.attributeValue("name").equals(name)) {
                    // 如果bean name相同则开始创建对象
                    String clazz = bean.attributeValue("class");
                    // 通过反射来创建对象
                    Class beanClass = Class.forName(clazz);
                    object = beanClass.newInstance();
                    List<Element> propertys = bean.elements();
                    if (propertys.size() > 0) {
                        for (Element property : propertys) {
                            String key = property.attributeValue("name");
                            Field field = beanClass.getDeclaredField(key);
                            field.setAccessible(true);

                            List<Element> childBean = property.elements();

                            // 如果property下内嵌bean
                            if (childBean.size() > 0) {
                                field.set(object, getBean(key, property));
                            }

                            if (property.attribute("ref") != null) {
                                /*
                                 * 此属性的值是一个对象.这里由于直接调用getBean方法赋值给对象,返回的对象一定是Bean参数的对象, 因此强制转换不会出问题
                                 */
                                String refid = property.attributeValue("ref");
                                field.set(object, getBean(refid, property));
                            } else {
                                /*
                                 * 此属性值是一个字符串.这里单独处理int,float类型变量.如果不处理,会将String类型直接赋值给int类型,
                                 * 发生ClassCastException
                                 */
                                String value = property.attributeValue("value");
                                // 需要对类型进行判断
                                field.set(object, value);// 处理String类型
                            }
                        }
                    }

                }
            }
        }

        return object;
    }

    public Map getAllBeans(Class type) throws Exception {
        Map map = new HashMap<>();
        try {
            Element root = document.getRootElement();
            List<Element> beans = root.elements();
            if (beans.size() > 0) {
                for (Element bean : beans) {
                    //反射创建对象
                    String class_s = bean.attributeValue("class");
                    Class beanClass = Class.forName(class_s);
                    Object object =  beanClass.newInstance();
                    //对象属性
                    List<Element> propertys = bean.elements();
                    if (propertys.size() > 0) {
                        for (Element property : propertys) {
                            String key = property.attributeValue("name");
                            Field field = beanClass.getDeclaredField(key);
                            field.setAccessible(true);
                            //内嵌的bean
                            List<Element> childBean = property.elements();
                            if (childBean.size() > 0) {
                                Object childObject = getBean(key, property);
                                field.set(object, childObject);
                            }
                            if (property.attribute("ref") != null) {
                                String refid = property.attributeValue("ref");
                                if (map.containsKey(refid))
                                    field.set(object, map.get(refid));
                                field.set(object, getBean(refid, root));
                            }
//                            else if (property.attributeValue("name").equals("id")) {
//
//                                Integer value = Integer.valueOf(property.attributeValue("value"));
//                                field.set(object, value);
//                            }
                            else {
                                //String类型
                                String value = property.attributeValue("value");
                                field.set(object, value);
                            }
                        }
                    }
                    if (object.getClass().equals(type))
                        map.put(bean.attributeValue("name"), object);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
