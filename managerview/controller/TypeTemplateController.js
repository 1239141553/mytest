app.controller('TypeTemplateController',function($scope,$controller,TypeTemplateService,BrandService,SpecificationService){
	
	//继承时必须要在HTML中引用
	$controller('baseController',{$scope:$scope});//继承
	
	$scope.brandList={data:[]};//定义品牌列表数据结构
	$scope.findBrandList = function() {
		BrandService.selectBrandOptionList().success(function(response) {
			$scope.brandList = {data:response};
		});
	}
	
	$scope.specList={data:[]};//定义品牌列表数据结构
	$scope.findSpecificationList = function() {
		SpecificationService.selectSpecificationOptionList().success(function(response) {
			$scope.SpecList = {data:response};
		});
	}
	
	$scope.save = function(){	
		var serviceObj = null;
		$scope.entity.brandIds = JSON.stringify($scope.entity.brandIds);
		$scope.entity.specIds = JSON.stringify($scope.entity.specIds);
		$scope.entity.customAttributeItems = JSON.stringify($scope.entity.customAttributeItems);
		if($scope.entity.id!=null){
			serviceObj = TypeTemplateService.updateTypeTemplate($scope.entity);
		}else{
			serviceObj = TypeTemplateService.addTypeTemplate($scope.entity);
		}
		serviceObj.success(function(response){
			if(response.code==200){
				$scope.reloadList();
			  }else{	
				alert(response.msg);						
			}
		});
	}
	
	$scope.queryTypeTemplateById = function(TypeTemplate_id){
		TypeTemplateService.queryTypeTemplateById(TypeTemplate_id).success(function(response){
			$scope.entity=response;
			
			$scope.entity.brandIds = JSON.parse($scope.entity.brandIds);
			$scope.entity.specIds = JSON.parse($scope.entity.specIds);
			$scope.entity.customAttributeItems = JSON.parse($scope.entity.customAttributeItems);
		});	
	}
		
	$scope.dele=function(){	
		if(confirm("是否确认删除")){
		TypeTemplateService.dele($scope.selectId).success(function(response){
			if(response.code==200){ 
				$scope.reloadList();
			}else{                  
				alert(response.msg);
           }                       
		});
	  }
	}
	

	$scope.search = function(pageNum,pageSize){
		TypeTemplateService.queryTypeTemplateByPage(pageNum,pageSize).success(function(response){
			$scope.TypeTemplateList=response.rows;
			$scope.paginationConf.totalItems=response.total;
		});	
	}
	
	$scope.queryTypeTemplateLike = function(){
		$scope.reloadList();
	}
	
	$scope.addTableRows=function(){	
		$scope.entity.customAttributeItems.push({});
	}
	
	$scope.deleteTableRows=function(index){		
		$scope.entity.customAttributeItems.splice(index,1);
	}
	
});