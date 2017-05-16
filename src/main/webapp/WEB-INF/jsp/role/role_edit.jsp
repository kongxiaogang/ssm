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
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/treeview/js/bootstrap-treeview.css">
  <%@ include file="../shared/importJs.jsp"%>
  <!-- DataTables -->
  <script src="<%=request.getContextPath()%>/resources/plugins/datatables/jquery.dataTables.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/plugins/datatables/dataTables.bootstrap.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/bootbox/bootbox.js"></script>
  <script src="<%=request.getContextPath()%>/resources/treeview/js/bootstrap-treeview.js"></script>
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
        <li><a href="#">角色管理</a></li>
        <li class="active">角色修改</li>
      </ol>
    </section>

	<!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
           <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">角色修改</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form name="roleform" class="form-horizontal" method="post" >
              <input name="permissions" type="hidden" id="permission">
              <input name="roleId" value="${role.roleId}" type="hidden" class="form-control" >
              <div class="box-body">
                <div class="form-group">
                  <label for="roleCnname" class="col-sm-2 control-label">角色中文名称：</label>
                  <div class="col-sm-10">
                  	<input id="roleCnname" name="roleCnname" value="${role.roleCnname }" type="text" class="form-control" placeholder="Enter ..." style="width: 70%;">
                  </div>
                </div><div class="form-group">
                  <label for="roleEnname" class="col-sm-2 control-label">角色英文名称：</label>
                  <div class="col-sm-10">
                  	<input id="roleEnname" name="roleEnname" value="${role.roleEnname }" type="text" class="form-control" placeholder="Enter ..." style="width: 70%;">
                  </div>
                </div>
                <div class="form-group">
                  <label for="roleDes"  class="col-sm-2 control-label">角色描述：</label>
                  <div class="col-sm-10">
                  	<textarea id="roleDes" name="roleDes" class="form-control" rows="3" placeholder="Enter ..." style="width: 70%;">${role.roleDes }</textarea>
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputEmail3" class="col-sm-2 control-label">权限选择：</label>
                  <div class="col-sm-10">
                  	<div class="col-sm-9">
          				<div id="treeview" class=""></div>
        			</div>
                  </div>
              	</div>
              	
              </div>
              <!-- /.box-body -->
              <div class="box-footer">
                <button type="button" class="btn btn-default pull-right " onclick="goBack()">返回</button>
                <button type="submit" class="btn btn-info pull-right" onclick="formSubmit()">提交</button>
              </div>
              <!-- /.box-footer -->
            </form>
          </div>
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
	//返回
	function goBack(){
		window.history.back(-1); 
	}
	//更新提交
	function formSubmit(){
		var treenodes = $('#treeview').treeview('getChecked',{ silent: true });
		var pers='';
		for (var index in treenodes) {
            if(index==treenodes.length-1) {
            	pers=pers+treenodes[index].nodeid;
            } else {
            	 pers=pers+treenodes[index].nodeid+",";
            }
        }
		document.getElementById('permission').value=pers;
		this.document.roleform.action="<%=request.getContextPath()%>/role/editRole.do";
        this.document.roleform.submit();
	}
</script>
<script>
  $(function () {
  	var $tree = $('#treeview').treeview({
		color:"#428bca",
		showCheckbox: true,
		showIcon: false,
		showBorder: false,
		selected:false,
		onNodeSelected: function(event, node) {
			$('#treeview').treeview('toggleNodeChecked', [ node.nodeId, { silent: true } ]);
			//$('#checkable-output').prepend('<p>' + node.text + ' was checked</p>');
		},
		data: <%=request.getAttribute("TreeJson")%>
	}); 
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
