<%--
  Created by IntelliJ IDEA.
  User: PengHusile
  Date: 2019/12/23
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询课程</title>
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
    <el-form :inline="true" class="demo-form-inline"  :model="course" label-width="80px" >
        <el-form-item label="课程id">
            <el-input v-model="course.id" width="80px"></el-input>
        </el-form-item>
            <el-button type="primary" @click="onSubmit">确定</el-button>
        </el-form-item>
    </el-form>
    <template style="width: 100%">
        <el-table :data="courseTable" style="width: 100%">
            <el-table-column prop="id" label="课程id" width="200px"></el-table-column>
            <el-table-column prop="name" label="课程名称" width="200px"></el-table-column>
            <el-table-column prop="teacher" label="任课教师" width="200px"></el-table-column>
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
                course: {
                    id: '',
                    name: '',
                    teacher:''
                },
                courseTable: []
            }

        },
        mounted() {

        },
        methods: {
            onSubmit() {
                let course = JSON.parse(JSON.stringify(this.course));
                console.log(course)
                var _this = this;
                axios({
                    method: 'post',
                    url: "/selectCourseByID",
                    data: course,
                    headers: {
                        "Content-Type": "application/json;charset=utf-8"  //头部信息
                    }
                }).then(response => {
                        if (response) {
                            _this.courseTable = response.data;
                            // var obj=JSON.parse(JSON.stringify(response.data));
                            console.log(response.data);
                            this.courseTable = _this.courseTable;
                        }
                        else alert("查询失败")
                    }
                ).catch(response => {
                        alert('查询错误');
                        console.info(response.data);
                    },
                );
            }
        }
    });
    // var Ctor = Vue.extend(Main)
    // new Ctor().$mount('#app')
</script>
