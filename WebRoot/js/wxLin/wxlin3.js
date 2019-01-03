$(document).ready(function(){
	  $("#name").focus(function(){	  
		  $("#tips").hide(500);
	  });
});
function legitimate(){
	var name = document.getElementById("name");
	if($.trim(name.value)==""){
		 $("#tips").show(300);
	}else{
		post();
	}
}
//function deleteHtml(){
//	var name = document.getElementById("name");
//	var depict = document.getElementById("depict");
//	name.value=delHtmlTag(name.value);
//	depict.value=delHtmlTag(depict.value);
//	post();
//}
//function delHtmlTag(str){
//	 return str.replace(/<[^>]+>/g,"");//去掉所有的html标记  
//}
