app.service('TypeTemplateService',function($http){
	var query_url= "http://localhost:8888/shop-query/";
	var edit_url= "http://localhost:8888/shop-edit/";
	
	this.addTypeTemplate = function(TypeTemplate_entity){
		return $http.post(edit_url+'addTypeTemplate',TypeTemplate_entity);
	}
	
	this.updateTypeTemplate = function(TypeTemplate_entity){
		return $http.post(edit_url+'updateTypeTemplate',TypeTemplate_entity);
	}
	
	this.queryTypeTemplateById = function(TypeTemplate_id){
		return $http.get(query_url+'queryTypeTemplateById?id='+TypeTemplate_id);
	}
	
	
	this.dele = function(ids){
		return $http.post(edit_url+"delTypeTemplate",ids);									
	}
	
	this.queryTypeTemplateByPage = function(pageNum,pageSize){
		return $http.get(query_url+"queryTypeTemplateByPage?pageNum="+pageNum+"&pageSize="+pageSize);									
	}
	
	this.queryAllTypeTemplate = function(){
		return $http.get(query_url+"queryAllTypeTemplate");									
	}
	
	this.queryTypeTemplateLike = function(pageNum,pageSize,searchContent){
		return $http.post(query_url+"queryTypeTemplateLike?pageNum="+pageNum+"&pageSize="+pageSize,searchContent);									
	}
});