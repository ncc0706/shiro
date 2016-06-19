

$(function(){
	$('#et').tree({
	    onClick: function(node){
	    	alert("ok");
		    if(node.attributes.url=='/#'){
		    	 $.messager.alert("系统提示","该菜单不支持跳转","info");
				 return;
		    }
			addTab(node.text,'<%=path%>'+node.attributes.url);
	    }
	});
});
