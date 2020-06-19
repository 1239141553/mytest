app.controller('SpecificationController',function($scope,SpecificationService){
	
	$scope.save = function(){	
		var serviceObj = null;
		if($scope.entity.specification.id!=null){
			serviceObj = SpecificationService.updateSpecification($scope.entity);
		}else{
			serviceObj = SpecificationService.addSpecification($scope.entity);
		}
		serviceObj.success(function(response){
			if(response.code==200){
				$scope.reloadList();
			  }else{	
				alert(response.msg);						
			}
		});
	}
	
	$scope.querySpecificationById = function(specification_id){	
		SpecificationService.querySpecificationById(specification_id).success(function(response){
			$scope.entity=response;
		});
	}
		
	$scope.dele=function(){	
		if(confirm("是否确认删除")){
		SpecificationService.dele($scope.selectId).success(function(response){
			if(response.code==200){ 
				$scope.reloadList();
			}else{                  
				alert(response.msg);
           }                       
		});
	  }
	}
	
	$scope.search = function(pageNum,pageSize){
		SpecificationService.querySpecificationByPage(pageNum,pageSize).success(function(response){
			$scope.specificationList=response.rows;
			$scope.paginationConf.totalItems=response.total;
		});	
	}
	
	$scope.querySpecificationLike = function(){
		$scope.reloadList();
	}
	
	$scope.addTableRows=function(){	
		$scope.entity.specificationOptions.push({});
	}
	
	$scope.deleteTableRows=function(index){		
		$scope.entity.specificationOptions.splice(index,1);
	}
	
});