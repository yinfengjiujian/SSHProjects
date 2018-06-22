function DataCenter(datacenter){
	this.clear();
	if (datacenter.header) {
		this.header = datacenter.header;
	}
	if (datacenter.body) {
		this.parameters = datacenter.body.parameters;
	}
	var dStroe = datacenter.body.dataStores;
	for (_dataStore in  dStroe) {
		this.dataStores[_dataStore] = new DataStore(_dataStore, dStroe[_dataStore]);			
	};
};
DataCenter.prototype = {
	 getParameter : function (name) {
	  	var value = this.parameters[name];
		if (value != "undefined" ) {
			if (typeof value == "array" || value instanceof Array){
				return value[0];
			}
			return value;
		}
	  },
	  
  	addParameter:function(name, value) {
	  	if (typeof this.parameters[name] == "array" || this.parameters[name] instanceof Array) {
		 	this.parameters[name].push(value);
	  	}else{
		 	this.parameters[name] = value;
	 	}
   	},
  
	setParameter : function(name, value){
		this.parameters[name] = value;
	},
	
	removeParameter: function(name){
		delete this.parameters[name];
	},
	
	getCode: function() {
		return this.header.code;
	},

	getTitle: function() {
		return this.header.message.title;
	},

	getDetail: function() {
		return this.header.message.detail;
	},
	
	getHeaderAttribute : function(name){
		return this.header[name];
	},
	
	addHeaderAttribute : function(name,value){
		this.header[name] = value;
	},
	
	clear: function() {
			this.header = {
				code:0,
				message:{
					title:"",
					detail:""
				}
			};
			this.parameters = {};
			this.dataStores = {};
	},
	
	getDataStore: function(name) {
		return this.dataStores[name];
	},
};