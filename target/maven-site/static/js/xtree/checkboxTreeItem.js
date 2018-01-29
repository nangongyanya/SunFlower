/*
 *	Sub class that adds a check box in front of the tree item icon
 *
 *	Created by Erik Arvidsson (http://webfx.eae.net/contact.html#erik)
 *
 *	Disclaimer:	This is not any official WebFX component. It was created due to
 *				demand and is just a quick and dirty implementation. If you are
 *				interested in this functionality the contact us
 *				http://webfx.eae.net/contact.html
 *
 *	Notice that you'll need to add a css rule the sets the size of the input box.
 *	Something like this will do fairly good in both Moz and IE
 *	
 *	input.tree-check-box {
 *		width:		auto;
 *		margin:		0;
 *		padding:	0;
 *		height:		14px;
 *		vertical-align:	middle;
 *	}
 *
 */

 var disableColor = webFXTreeConfig.disableColor;

function WebFXCheckBoxTreeItem(sText, sValue, eParent, sIcon, sOpenIcon, bChecked, disabled) {
	this.base = WebFXTreeItem;
	this.base(sText, null, eParent, sIcon, sOpenIcon);	
	this._checked = bChecked;	
	this._disabled = false;
	if(disabled) this._disabled = disabled;

    // luohc 2004-7-30.
	this.value = sValue==null?"":sValue;	
	if(bChecked != null && bChecked == true){
		checkedValues.put(this.id,  new CheckedObject(this.id, sText, sValue));
	}
}

WebFXCheckBoxTreeItem.prototype = new WebFXTreeItem;

WebFXCheckBoxTreeItem.prototype.toString = function (nItem, nItemCount) {
	var foo = this.parentNode;
	var indent = '';
	if (nItem + 1 == nItemCount) { this.parentNode._last = true; }
	var i = 0;
	while (foo.parentNode) {
		foo = foo.parentNode;
		indent = "<img id=\"" + this.id + "-indent-" + i + "\" src=\"" + ((foo._last)?webFXTreeConfig.blankIcon:webFXTreeConfig.iIcon) + "\">" + indent;
		i++;
	}
	this._level = i;
	if (this.childNodes.length) { this.folder = 1; }
	else { this.open = false; }
	if ((this.folder) || (webFXTreeHandler.behavior != 'classic')) {
		if (!this.icon) { this.icon = webFXTreeConfig.folderIcon; }
		if (!this.openIcon) { this.openIcon = webFXTreeConfig.openFolderIcon; }
	}
	else if (!this.icon) { this.icon = webFXTreeConfig.fileIcon; }
	var label = "&nbsp;" + this.text.replace(/</g, '&lt;').replace(/>/g, '&gt;');

	var str = "<div id=\"" + this.id + "\" ondblclick=\"webFXTreeHandler.toggle(this);\" class=\"webfx-tree-item\" onkeydown=\"return webFXTreeHandler.keydown(this, event)\">";
	str += indent;
	str += "<img id=\"" + this.id + "-plus\" src=\"" + ((this.folder)?((this.open)?((this.parentNode._last)?webFXTreeConfig.lMinusIcon:webFXTreeConfig.tMinusIcon):((this.parentNode._last)?webFXTreeConfig.lPlusIcon:webFXTreeConfig.tPlusIcon)):((this.parentNode._last)?webFXTreeConfig.lIcon:webFXTreeConfig.tIcon)) + "\" onclick=\"webFXTreeHandler.toggle(this);\">"
	
	// insert check box
	var tempStr = "<input type=\"checkbox\"" + " class=\"tree-check-box\"" +
		(this._checked ? " checked=\"checked\"" : "") +
		" onclick=\"webFXTreeHandler.all[this.parentNode.id].setChecked(this.checked)\"" +
		" value=\"" + this.value + "\" id=\"" + this.id + "-input\" name=\"" + this.value 
		+ "-input\" " + (this._disabled?" disabled ": "") + ">";
	str += tempStr;	
	// end insert checkbox
	
	//str += "<img id=\"" + this.id + "-icon\" class=\"webfx-tree-icon\" src=\"" + ((webFXTreeHandler.behavior == 'classic' && this.open)?this.openIcon:this.icon) + "\" onclick=\"webFXTreeHandler.select(this);\"><a href=\"" + this.action + "\" id=\"" + this.id + "-anchor\" onfocus=\"webFXTreeHandler.focus(this);\" onblur=\"webFXTreeHandler.blur(this);\">" + label + "</a></div>";
	tempStr = "<img id=\"" + this.id + "-icon\" class=\"webfx-tree-icon\" src=\"" + ((webFXTreeHandler.behavior == 'classic' && this.open)?this.openIcon:this.icon) + "\" onclick=\"webFXTreeHandler.select(this);\"><span  id=\"" + this.id + "-anchor\" onfocus=\"webFXTreeHandler.focus(this);\" onblur=\"webFXTreeHandler.blur(this);\" style='cursor:hand;color:" + this.getColor() + "'>" + label + "</span></div>";
	str += tempStr;

	str += "<div id=\"" + this.id + "-cont\" class=\"webfx-tree-container\" style=\"display: " + ((this.open)?'block':'none') + ";\">";
	for (var i = 0; i < this.childNodes.length; i++) {
		str += this.childNodes[i].toString(i,this.childNodes.length);
	}
	str += "</div>";
	this.plusIcon = ((this.parentNode._last)?webFXTreeConfig.lPlusIcon:webFXTreeConfig.tPlusIcon);
	this.minusIcon = ((this.parentNode._last)?webFXTreeConfig.lMinusIcon:webFXTreeConfig.tMinusIcon);
	return str;
}

WebFXCheckBoxTreeItem.prototype.getChecked = function () {
	//var divEl = document.getElementById(this.id);
	//var inputEl = divEl.getElementsByTagName("INPUT")[0];
	var inputEl = getCheckBox(this.id);
	return this._checked = inputEl.checked;
};

WebFXCheckBoxTreeItem.prototype.setChecked = function (bChecked) {
    // �������޺鳼�޸� 2004-7-30.
	/*
	if (bChecked != this.getChecked()) {
		var divEl = document.getElementById(this.id);
		var inputEl = divEl.getElementsByTagName("INPUT")[0];
		this._checked = inputEl.checked = bChecked;
		
		if (typeof this.onchange == "function")
			this.onchange();
	}*/
	this._checked = bChecked;	
	doCheck(this, bChecked);
	if (typeof this.onchange == "function"){
		this.onchange(this.text, this.value, bChecked);
	}else if(this.onchange != null && this.onchange != ""){
		var str = this.onchange + "('" + this.text + "','" + this.value + "'," + bChecked + ");";
		eval(str);
	}
};

/*****   �����ǵݹ�ѡ��CheckBox�ķ���   �޺鳼   2004-7-30   *******/
var checkedValues = new Map();

function CheckedObject(id, text, value){
	this.id = id;
	this.text = text;
	this.value = value;
}

CheckedObject.prototype.toString = function(){
	var str = "\nid = " + this.id + "\ntext = " + this.text + "\nvalue = " + this.value + "\n";
	return str;
}


function setCheckedValues(node, bChecked){
	if(bChecked){		
		checkedValues.put(node.id,  new CheckedObject(node.id, node.text, node.value));		
	}else{		
		checkedValues.remove(node.id);
	}
}

function getCheckBox(id){
	return document.getElementById(id + "-input");
}

function doCheck(item, bChecked){
	setCheckedValues(item, bChecked);	
	// �Ƿ�jѡ��.
	if(webFXTreeConfig.cascadeCheck){
		checkChildren(item, bChecked);
		if(!bChecked){
//			 unCheckParents(item);
		}else{
			checkParents(item);
		}
	}
}

// ���ø��ڵ�Ϊѡ��״̬.����ӽڵ㶼��ѡ�еĻ���
function checkParents(item){
	var pnode = item.parentNode;
	if(pnode instanceof WebFXCheckBoxTreeItem){
		/*for(var i=0; i<pnode.childNodes.length; i++){
			 var node = pnode.childNodes[i];
			 var cbx = getCheckBox(node.id);
			 if(cbx == null || !cbx.checked) {				 				 
				 //pnode.getCheckBox().checked = false;
				 //setCheckedValues(pnode, false);
				 return;
			 }
		 }*/ // end for.		 		 
		 getCheckBox(pnode.id).checked = true;
		 setCheckedValues(pnode, true);
		 checkParents(pnode);
	}   
}

// �ӽڵ�δѡ��ʱ���丸�ڵ���Ϊδѡ��״̬.
function unCheckParents(item){
	var pNode = item.parentNode;
	while(pNode instanceof WebFXCheckBoxTreeItem){
		var cbx = getCheckBox(pNode.id);
		if(cbx.checked){
		   cbx.checked = false;
		   setCheckedValues(pNode, false);		   
		   pNode = pNode.parentNode;
		}else{
			return;
		}		
	}
}

// ѭ����ǰ���ڵ�������ӽڵ�.
function checkChildren(item, bChecked){
	checkNode(item); // ���ء�չ���ӽڵ�.
	for(var i=0; i<item.childNodes.length; i++){
	    var node = item.childNodes[i];		
		if(node instanceof WebFXCheckBoxTreeItem){
			checkChildren(node, bChecked);
		    var cbx = getCheckBox(node.id);		 
			if(!cbx.disabled){
				cbx.checked = bChecked;
				setCheckedValues(node, bChecked);
			}			
		}
	}	
}

// ���ء�չ���ӽڵ�
function checkNode(item){  
	if(item.loadChildren) item.loadChildren();
    if(item.childNodes.length > 0)  item.expand();
}


// ��ȡѡȡ�Ķ���.
function getCheckObjects(includeDisabled){
	var array = new Array();
	var values = checkedValues.getValues();
	for(var i=0; i<values.length; i++){
		var obj = values[i];
		if(obj != null){
			if(includeDisabled){
				array[array.length] = obj;
			}else{
			    var cbx = getCheckBox(obj.id);
			    if(!cbx.disabled) array[array.length] = obj;
			}
		}
	}
	return array;
}


// ��ȡ���е�ѡ��CheckBox��ֵ. 
// includeDisabled �Ƿ��(disabled״̬��CheckBox, Ĭ��ֵ��false.
function getCheckValues(includeDisabled){	
	var array = new Array();
	var values = checkedValues.getValues();
	for(var i=0; i<values.length; i++){
		var obj = values[i];
		if(obj != null){
			if(includeDisabled){
				array[array.length] = obj.value;
			}else{
			    var cbx = getCheckBox(obj.id);
			    if(!cbx.disabled) array[array.length] = obj.value;
			}
		}
	}
	return array;
}


// ����ѡ�� checkbox ���ı���
function getCheckTexts(includeDisabled){		
	var array = new Array();
	var values = checkedValues.getValues();
	for(var i=0; i<values.length; i++){
		var obj = values[i];
		if(obj != null){
			if(includeDisabled){
				array[array.length] = obj.text;
			}else{
			    var cbx = getCheckBox(obj.id);
			    if(!cbx.disabled) array[array.length] = obj.text;
			}
		}
	}
	return array;
}

// ����CheckBox��ѡ��״̬.
function setCheckBox(value, checked, recursive){
	var cbxs = document.getElementsByName(value + "-input");
	if(cbxs && cbxs.length >0){
		for(var i=0; i<cbxs.length; i++){
			var cbx = cbxs[i];
			   //if(!cbx.disabled)
			   cbx.checked = checked;		
			   var id = cbx.id.substring(0, cbx.id.length-6);	        
			   var node = webFXTreeHandler.all[id];
			   setCheckedValues(node, checked);
			   if(recursive){				   
				   doCheck(node, checked);
			   }
		}
	}
}

// ����CheckBox�Ŀ���״̬.
function disableCheckBox(id, disabled, recursive){
	var cbxs = document.getElementsByName(id + "-input");
	if(cbxs && cbxs.length >0){
		for(var i=0; i<cbxs.length; i++){
			var cbx = cbxs[i];
			cbx.disabled = disabled;	
			var id = cbx.id.substring(0, cbx.id.length-6);
	        var span = document.getElementById(id+ "-anchor");
	        if(span){
				if(disabled) span.style.color = disableColor;
				else span.style.color = "black";
			}
			if(recursive){			
				var node = webFXTreeHandler.all[id];
				disabledChildren(node, disabled);
			}			
		}
	}	
}

function disabledChildren(node, disabled){
  for(var i=0; i<node.childNodes.length; i++){
	  var item = node.childNodes[i];
	  var id = item.id;
	  var cbx = document.getElementById(id + "-input");
	  if(cbx)cbx.disabled  = disabled;	 
	  var span = document.getElementById(id + "-anchor");
	  if(span){
	    	if(disabled) span.style.color = disableColor;
		    	else span.style.color = "black";
	  }
	  disabledChildren(item, disabled);
  }
}

// ����CheckBox.
function visiableCheckBox(visiable){
	if(visiable){ 
		disp = "";
	}else{
		disp = "none";
	}
	var cbxs = document.getElementsByTagName("INPUT");
	if(cbxs && cbxs.length >0){
		for(var i=0; i<cbxs.length; i++){
			var cbx = cbxs[i];
			if(cbx.type.toUpperCase() == "CHECKBOX"){
				cbx.style.display = disp;
			}
		}
	}
}

