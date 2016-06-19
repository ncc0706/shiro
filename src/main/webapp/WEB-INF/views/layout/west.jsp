<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<div class="easyui-accordion" data-options="fit:true,border:false" style="overflow:auto;">
  	<ul id="et" class="easyui-tree" data-options="url:'${pageContext.request.contextPath }/static/menu.json',method:'get',animate:true,dnd:true"></ul>
</div>