<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Blank Page - Ace Admin</title>
<c:import url="/pages/inc/inc_head.jsp" />
<link href="/static/cropper/dist/cropper.css" rel="stylesheet">
<link href="/static/cropper/demo/css/main.css" rel="stylesheet">
</head>

<body class="no-skin">
	<c:import url="/pages/inc/inc_navbar.jsp" />

	<div class="main-container ace-save-state" id="main-container">
		<c:import url="/pages/inc/inc_sidebar.jsp?menu=100-1" />

		<div class="main-content">
			<div class="main-content-inner">
				<c:import url="/pages/inc/inc_breadcrumbs.jsp?menu=空白页" />

				<div class="page-content">
					<div class="row">
				      <div class="col-md-9">
				        <!-- <h3 class="page-header">Demo:</h3> -->
				        <div class="img-container">
				          <img src="/static/cropper/assets/img/picture.jpg" alt="Picture">
				        </div>
				      </div>
				      <div class="col-md-3">
				        <!-- <h3 class="page-header">Preview:</h3> -->
				        <div class="docs-preview clearfix">
				          <div class="img-preview preview-lg"></div>
				        </div>
				        
				        <!-- <h3 class="page-header">Data:</h3> -->
				        <div class="docs-data">
				          <div class="input-group">
				            <label class="input-group-addon" for="dataWidth">Width</label>
				            <input class="form-control" id="dataWidth" type="text" placeholder="width">
				            <span class="input-group-addon">px</span>
				          </div>
				          <div class="input-group">
				            <label class="input-group-addon" for="dataHeight">Height</label>
				            <input class="form-control" id="dataHeight" type="text" placeholder="height">
				            <span class="input-group-addon">px</span>
				          </div>
				        </div>
				        
				        <div class="btn-group">
				          <label class="btn btn-primary btn-upload" for="inputImage" title="Upload image file">
				            <input class="sr-only" id="inputImage" name="file" type="file" accept="image/*">
				            <span class="docs-tooltip" data-toggle="tooltip" title="上传图片">
				              <span class="icon icon-upload"></span>
				            </span>
				            </label>
				            <button class="btn btn-primary js-ok" type="button">
								<span class="docs-tooltip"> 
									<span class="icon icon-off"></span>
								</span>
							</button> 
				        </div>
				      </div>
				    </div>
				</div>
			</div>
		</div>
		<!-- /.main-content -->
		
		<!-- Alert -->
  		<div class="docs-alert"><span class="warning message"></span></div>

		<c:import url="/pages/inc/inc_footer.jsp" />
	</div>
	<!-- /.main-container -->

	<c:import url="/pages/inc/inc_script.jsp" />
	<script src="/static/cropper/dist/cropper.js"></script>
  	<script src="/static/cropper/demo/js/main.js"></script>
</body>
</html>