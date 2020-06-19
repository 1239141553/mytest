app.service('SpecificationService',function($http){
	var query_url= "http://localhost:8888/shop-query/";
	var edit_url= "http://localhost:8888/shop-edit/";
	
	this.addSpecification = function(specification_entity){
		return $http.post(edit_url+'addSpecification',specification_entity);
	}
	
	this.updateSpecification = function(specification_entity){
		return $http.post(edit_url+'updateSpecification',specification_entity);
	}
	
	this.querySpecificationById = function(specification_id){
		return $http.get(query_url+'querySpecificationById?id='+specification_id);
	}
	
	
	this.dele = function(ids){
		return $http.post(edit_url+"delSpecification",ids);									
	}
	
	this.querySpecificationByPage = function(pageNum,pageSize){
		return $http.get(query_url+"querySpecificationByPage?pageNum="+pageNum+"&pageSize="+pageSize);									
	}
	
	this.querySpecificationLike = function(pageNum,pageSize,searchContent){
		return $http.post(query_url+"querySpecificationLike?pageNum="+pageNum+"&pageSize="+pageSize,searchContent);									
	}
	
	this.selectSpecificationOptionList = function(){
		return $http.get(query_url+'selectSpecificationOptionList');
	}
});