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
    <title>更新课程</title>
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
                    <el-button type="primary" @click="updateCourse(scope.$index, scope.row)">更新</el-button>
                    <el-button type="primary" @click="deleteCourse(scope.$index, scope.row)">编辑</el-button>
                </template>
            </el-table-column>
            <el-table-column
                    label="日期"
                    width="180">
                <template scope="scope">
                    <span v-if="!scope.row.editeFlag">{{ scope.row.name }}</span>
                    <span v-if="scope.row.editeFlag" class="cell-edit-input">
                        <el-input v-model="scope.row.name"
                                  placeholder="请输入内容"></el-input></span>
                    <span v-if="!scope.row.editeFlag" style="margin-left:10px;" class="cell-icon"
                          @click="handleEdit(scope.$index,scope.row)">  <i class="el-icon-edit"></i> </span>
                    <span v-if="scope.row.editeFlag" style="margin-left:10px;" class="cell-icon"
                          @click="handleSave(scope.$index,scope.row)">  <i class="el-icon-document"></i> </span>
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
            updateCourse(index, row) {
                let course = JSON.parse(JSON.stringify(row));
                console.log(course)
                var _this = this;
                axios({
                    method: 'post',
                    url: "/updateCourse",
                    data: course,
                    headers: {
                        "Content-Type": "application/json;charset=utf-8"  //头部信息
                    }
                }).then(response => {
                        if (response) {
                            alert("删除成功")
                        } else alert("删除失败")
                    }
                ).catch(response => {
                        alert('删除错误');
                        console.info(response.data);
                    },
                );
            },
            handleEdit: function (index, row) {
                //遍历数组改变editeFlag
                console.log(row)
                console.log(index);
                this.tableData[index].editeFlag = true;


            },
            handleSave: function (index, row) {
                //保存数据，向后台取数据
                this.tableData[index].editeFlag = false;
            },
            handleMouseEnter: function (row, column, cell, event) {


                cell.children[0].children[1].style.color = "black";
            },
            handleMouseOut: function (row, column, cell, event) {


                cell.children[0].children[1].style.color = "#ffffff";
            }
        }
    });
    // var Ctor = Vue.extend(Main)
    // new Ctor().$mount('#app')
</script>
