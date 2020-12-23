<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <!DOCTYPE html>
<html>


<!-- Mirrored from www.gzsxt.cn/theme/hplus/table_basic.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:01 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>办公系统 - 基础表格</title>
    <meta name="keywords" content="办公系统">
    <meta name="description" content="办公系统">

    <link rel="shortcut icon" href="favicon.ico"> 
    	<link href="css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="css/plugins/select/bootstrap-select.min.css" rel="stylesheet">

</head>

<body class="gray-bg">
	<div class="wrapper2 wrapper-content2 animated fadeInRight">
	       <div class="row">
	    		<div class="col-sm-5">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>添加角色</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal" action="/add-role" method="post">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">角色名称：</label>

                                <div class="col-sm-8">
                                    <input type="text" name="rolename" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">角色描述：</label>

                                <div class="col-sm-8">
                                    <input type="text"  name="roledis" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">角色权限：</label>

                                <div class="col-sm-8">
                                     <c:forEach items="${menuList}" var="menu">
                                         <input type="checkbox" name="sourcesListId" value="${menu.id}">${menu.name}<br/>
                                     </c:forEach>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否启用：</label>
                                <div class="col-sm-8">
                                    <div class="switch">
			                            <div class="onoffswitch">
			                                <input type="checkbox" checked class="onoffswitch-checkbox" id="status" name="status" value="1">
			                                <label class="onoffswitch-label" for="status">
			                                    <span class="onoffswitch-inner"></span>
			                                    <span class="onoffswitch-switch"></span>
			                                </label>
			                            </div>
			                        </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-8">
                                    <button class="btn btn-sm btn-white" type="submit"><i class="fa fa-save"></i> 保存</button>
                                    <button class="btn btn-sm btn-white" type="reset"><i class="fa fa-undo"></i> 重置</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
	    		<div class="col-sm-7">
                  <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>角色列表 <small>点击修改信息将显示在左边表单</small></h5>
                    </div>
                    <div class="ibox-content">                   		
                        <div class="hr-line-dashed2"></div>
                        <div class="row">
                            <div class="table-responsive">
                            <table class="table table-striped list-table">
                                <thead>
                                    <tr>		
                                        <th>选择</th>
                                        <th>角色名称</th>
                                        <th>角色描述：</th>
                                        <th>是否启用</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${roleIPage != null}">
                                        <c:forEach items="${roleIPage.records}" var="role">
                                            <tr>
                                                <td><input  type="checkbox" checked=""></td>
                                                <td>${role.rolename}</td>
                                                <td>${role.roledis}</td>
                                                <td>
                                                    ${role.status == 1 ? "是" : "否"}
                                                </td>
                                                <td>
                                                    <a href="/to-update-role?roleid=${role.roleid}"><i class="glyphicon glyphicon-edit  text-navy"></i></a>
                                                    <a href="/del-role?roleid=${role.roleid}" class="btndel"><i class="glyphicon glyphicon-remove text-navy"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>

                                    
                                </tbody>
                            </table>
                        </div>
                        <div class="row">
	                        	<div class="col-sm-5">
	                        		<button class="btn btn-sm btn-primary" type="button"><i class="fa fa-check-square-o"></i> 全选</button>
	                        		<button class="btn btn-sm btn-primary" type="button"><i class="fa fa-square-o"></i> 反选</button>
	                        		<button class="btn btn-sm btn-primary" type="button"><i class="fa fa-times-circle-o"></i> 删除</button>
	                        	</div>
							<div class="col-sm-7 text-right">
								<div class="btn-group">
	                              
								    <span>共有${roleIPage.getPages()}页,当前是第${roleIPage.current}页</span>
	                                <a href='/list-role'>首页</a>
	                                <a href='/list-role?pageNum=${roleIPage.current <=1  ? 1 : (roleIPage.current)-1 }&size=2'>上一页</a>
	                                <a href='/list-role?pageNum=${roleIPage.current >=roleIPage.getPages() ? roleIPage.getPages() : (roleIPage.current)+1 }&size=2'>下一页</a>
	                                <a href='/list-role?pageNum=${roleIPage.getPages()}&size=2'>尾页</a>
	                            </div>
							</div>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
            
	    	</div>
	
	</div>       
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
     <script src="js/plugins/select/bootstrap-select.min.js"></script>
    <script src="js/plugins/sweetalert/sweetalert.min.js"></script>

   <script>
	$(document).ready(function() {
		// 设置按钮的样式
		$('.selectpicker').selectpicker('setStyle', 'btn-white').selectpicker('setStyle', 'btn-sm');
		
		//点击删除
		$('.btndel').click(function () {
            swal("删除成功！", "您已经永久删除了这条信息。", "success");
		    // swal({
		    //     title: "您确定要改修该角色状态吗？",
		    //     text: "改修后将将，请谨慎操作！",
		    //     type: "warning",
		    //     showCancelButton: true,
		    //     confirmButtonColor: "#DD6B55",
		    //     confirmButtonText: "删除",
		    //     closeOnConfirm: false
		    // }, function () {//此函数是点击删除执行的函数
		    // 		//这里写ajax代码
		    // 		// 以下是成功的提示框，请在ajax回调函数中执行
			//     swal("删除成功！", "您已经永久删除了这条信息。", "success");
		    // });
		});
		
	});
    </script>
    
</body>


</html>
    
