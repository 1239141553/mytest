app.service("uploadService",function($http){
	
	this.uploadFile = function(){

		// 向后台传递数据:
		var formData = new FormData();
		// 向formData中添加数据    file必须和后台微服务的controller接口方法的参数名一致！
		formData.append("file",file.files[0]);		
		/*
		 * 
		 1. 因为是通过anjularjs的http请求来上传文件的，所以要让当前的request成为一个Multipart/form-data请求，
		    anjularjs对于post和get请求默认的Content-Type header 是application/json。
                               所以，通过设置headers : {‘Content-Type’ : undefined}这样浏览器不仅帮我们把Content-Type 设置为 multipart/form-data，
                              还填充上当前的boundary(boundary 是随机生成的字符串，用来分隔文本的开始和结束)，如果你手动设置为： ‘Content-Type’: multipart/form-data，
                              后台会抛出异常：the current request boundary parameter is null。
                     
              2.通过设置 transformRequest: angular.identity ，
                  anjularjs transformRequest function 将序列化我们的formdata object.
		 * */
		return $http({
			method:'post',
			url:'http://localhost:8083/shopping-content/uploadFile',
			data:formData,
			headers:{'Content-Type':undefined} ,// Content-Type : text/html  text/plain
			transformRequest: angular.identity
		});		
	}	
});