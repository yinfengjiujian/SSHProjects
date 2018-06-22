function Row(rowset, data, index){
	(this.rowset = rowset) && (this._e = (rowset.onItemChanging || rowset.onItemChanged));
	this.data = data;
	this.index = index;
};
Row.prototype={
	getData: function() {
		return this.data;
	},
	
	getRowSet: function() {
		return this.rowset;
	},
	
	getIndex: function() {
		return this.index;
	},
	
	getItemValue : function(name){
		return this.data[name];
	},
};