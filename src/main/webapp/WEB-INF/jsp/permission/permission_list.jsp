<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../shared/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | 星河财富</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@ include file="../shared/importCss.jsp"%>
  <!-- DataTables -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/plugins/datatables/dataTables.bootstrap.css">
  <%@ include file="../shared/importJs.jsp"%>
  <!-- DataTables -->
  <script src="<%=request.getContextPath()%>/resources/plugins/datatables/jquery.dataTables.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/plugins/datatables/dataTables.bootstrap.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/bootbox/bootbox.js"></script>
</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <%@ include file="../shared/pageHeader.jsp"%>
  <!-- Left side column. contains the logo and sidebar -->
  <%@ include file="../shared/sidebarMenu.jsp"%>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <small></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
        <li class="active">权限管理</li>
      </ol>
    </section>

	<!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">权限列表</h3>
              <div class="box-tools">
                <div class="input-group input-group-sm" style="width: 300px;">
                  <input type="text" name="table_search" class="form-control pull-right" placeholder="Search">
                  <div class="input-group-btn">
                    <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                    <button class="btn btn-default" onclick="">查询</button>
                  </div>
                  <div class="input-group-btn">
                    <button class="btn btn-default" onclick="addPermission('');">增加</button>
                  </div>
                </div>
              </div> 
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th>序号</th>
                  <th>权限名称</th>
                  <th>权限类型</th>
                </tr>
                </thead>
                <tbody>
	                <c:forEach items="${page.list}" var="permission" varStatus="status">
	                    <tr>
	                    	<td>${page.startRow+status.index}</td>
	                        <td>${permission.perName}</td>
	                        <td>${permission.perType==0?'菜单':permission.perType==1?'操作':'未知'}</td>
	                  		<th>
	                  			<button class="btn btn-xs btn-info" onclick="addPermission('${permission.menuId }');">添加下级</button>
	                  			<button class="btn btn-xs btn-info" onclick="editPermission('${permission.perId }','${permission.menuId }');">修改</button>
	                  			<button class="btn btn-xs btn-info" onclick="delPermission('${permission.perId }','${permission.perName }');">删除</button>
	                  		</th>
	                    </tr>
	                </c:forEach>
                </tbody>
              </table>
              <table class="gridtable" style="width:50%;text-align: right;">
               <tr>
                   <c:if test="${page.hasPreviousPage}">
                       <td><a href="showPermissionList.do?pageNum=${page.prePage}&pageSize=${page.pageSize}">前一页</a></td>
                   </c:if>
                   <c:forEach items="${page.navigatepageNums}" var="nav">
                       <c:if test="${nav == page.pageNum}">
                           <td style="font-weight: bold;">${nav}</td>
                       </c:if>
                       <c:if test="${nav != page.pageNum}">
                           <td><a href="showPermissionList.do?pageNum=${nav}&pageSize=${page.pageSize}">${nav}</a></td>
                       </c:if>
                   </c:forEach>
                   <c:if test="${page.hasNextPage}">
                       <td><a href="showPermissionList.do?pageNum=${page.nextPage}&pageSize=${page.pageSize}">下一页</a></td>
                   </c:if>
               </tr>
            </table>
            </div>
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
   
  </div>
  <!-- /.content-wrapper -->
  <%@ include file="../shared/pageFooter.jsp"%>

  <%@ include file="../shared/controlSidebar.jsp"%>
</div>
<script type="text/javascript">
	//删除权限
	function delPermission(permissionId,msg){
		bootbox.confirm("确定要删除["+msg+"]吗?", function(result) {
			if(result) {
				window.location.href = "<%=request.getContextPath()%>"+"/permission/deletePermission.do?id="+permissionId+"&tm="+new Date().getTime();
			}
		});
	}
	//更新权限
	function editPermission(permissionId,menuId){
		window.location.href = "<%=request.getContextPath()%>"+"/permission/showPermissionEdit.do?id="+permissionId+"&menuId="+menuId+"&tm="+new Date().getTime();
	}
	//添加权限
	function addPermission(parentId){
		window.location.href = "<%=request.getContextPath()%>"+"/permission/showPermissionAdd.do?parentId="+parentId+"&tm="+new Date().getTime();
	}
</script>
<script>
  $(function () {
    $('#example2').DataTable({
      "paging": false,
      "bFilter": false,
      "bSort": false,
      "info": false,
      "sInfo":false
    });
  });
</script>
</body>
</html>
