<%--
  Created by IntelliJ IDEA.
  User: PengHusile
  Date: 2019/12/23
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除课程</title>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入 Vue -->
    <script src="https://unpkg.com/vue/dist/vue.js"></script>
    <!-- 引入 JQuery -->
    <script type="text/javascript" src="../../../js/jquery-1.8.3.min.js"></script>
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="//cdn.bootcss.com/qs/6.5.2/qs.min.js"></script>
</head>
<body>
<div id="app">
    <!--根据数组数据自动渲染页面-->
    <template style="width: 100%">
        <el-table :data="courseTable" style="width: 100%">
            <el-table-column prop="id" label="课程id" width="200px"></el-table-column>
            <el-table-column prop="name" label="课程名称" width="200px"></el-table-column>
            <el-table-column prop="teacher" label="任课教师" width="200px"></el-table-column>
            <el-table-column label="操作" align="center" min-width="100">
                <template slot-scope="scope">                    　　　
                    <el-button type="danger" @click="deleteCourse(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </template>
</div>
</body>
</html>
<script>
    var Main = new Vue({
        el: "#app",
        type: "post",
        data: function () {
            return {
                courseTable: []
            }

        },
        created: function () {
            var _this = this;
            axios({
                method: 'post',
                url: "/loadAllCourse",
                data: '',
                headers: {
                    "Content-Type": "application/json;charset=utf-8"  //头部信息
                }
            }).then(response => {
                    if (response) {
                        _this.courseTable = response.data;
                        // var obj=JSON.parse(JSON.stringify(response.data));
                        console.log(response.data);
                        this.courseTable = _this.courseTable;
                    } else alert("加载失败")
                }
            ).catch(response => {
                    alert('加载错误');
                    console.info(response.data);
                },
            );
        },
        mounted() {

        },
        methods: {
            deleteCourse(index,row) {
                let course =JSON.parse(JSON.stringify(row)) ;
                console.log(course)
                var _this = this;
                axios({
                    method: 'post',
                    url: "/delCourse",
                    data: course,
                    headers: {
                        "Content-Type": "application/json;charset=utf-8"  //头部信息
                    }
                }).then(response => {
                        if (response) {
                            alert("删除成功")
                        }
                        else alert("删除失败")
                    }
                ).catch(response => {
                        alert('删除错误');
                        console.info(response.data);
                    },
                );
            }
        }
    });
    // var Ctor = Vue.extend(Main)
    // new Ctor().$mount('#app')
</script>
