/*----------------------------------------------------------------------------\
|                       Cross Browser Tree Widget 1.17                        |
|-----------------------------------------------------------------------------|
|                          Created by Emil A Eklund                           |
|                  (http://webfx.eae.net/contact.html#emil)                   |
|                      For WebFX (c/)                      |
|-----------------------------------------------------------------------------|
| An object based tree widget,  emulating the one found in microsoft windows, |
| with persistence using cookies. Works in IE 5+, Mozilla and konqueror 3.    |
|-----------------------------------------------------------------------------|
|                   Copyright (c) 1999 - 2002 Emil A Eklund                   |
|-----------------------------------------------------------------------------|
| This software is provided "as is", without warranty of any kind, express or |
| implied, including  but not limited  to the warranties of  merchantability, |
| fitness for a particular purpose and noninfringement. In no event shall the |
| authors or  copyright  holders be  liable for any claim,  damages or  other |
| liability, whether  in an  action of  contract, tort  or otherwise, arising |
| from,  out of  or in  connection with  the software or  the  use  or  other |
| dealings in the software.                                                   |
| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |
| This  software is  available under the  three different licenses  mentioned |
| below.  To use this software you must chose, and qualify, for one of those. |
| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |
| The WebFX Non-Commercial License          http://webfx.eae.net/license.html |
| Permits  anyone the right to use the  software in a  non-commercial context |
| free of charge.                                                             |
| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |
| The WebFX Commercial license           http://webfx.eae.net/commercial.html |
| Permits the  license holder the right to use  the software in a  commercial |
| context. Such license must be specifically obtained, however it's valid for |
| any number of  implementations of the licensed software.                    |
| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |
| GPL - The GNU General Public License    http://www.gnu.org/licenses/gpl.txt |
| Permits anyone the right to use and modify the software without limitations |
| as long as proper  credits are given  and the original  and modified source |
| code are included. Requires  that the final product, software derivate from |
| the original  source or any  software  utilizing a GPL  component, such  as |
| this, is also licensed under the GPL license.                               |
|-----------------------------------------------------------------------------|
| Dependencies: xtree.css (To set up the CSS of the tree classes)             |
|-----------------------------------------------------------------------------|
| 2001-01-10 | Original Version Posted.                                       |
| 2001-03-18 | Added getSelected and get/setBehavior  that can make it behave |
|            | more like windows explorer, check usage for more information.  |
| 2001-09-23 | Version 1.1 - New features included  keyboard  navigation (ie) |
|            | and the ability  to add and  remove nodes dynamically and some |
|            | other small tweaks and fixes.                                  |
| 2002-01-27 | Version 1.11 - Bug fixes and improved mozilla support.         |
| 2002-06-11 | Version 1.12 - Fixed a bug that prevented the indentation line |
|            | from  updating correctly  under some  circumstances.  This bug |
|            | happened when removing the last item in a subtree and items in |
|            | siblings to the remove subtree where not correctly updated.    |
| 2002-06-13 | Fixed a few minor bugs cased by the 1.12 bug-fix.              |
| 2002-08-20 | Added usePersistence flag to allow disable of cookies.         |
| 2002-10-23 | (1.14) Fixed a plus icon issue                                 |
| 2002-10-29 | (1.15) Last changes broke more than they fixed. This version   |
|            | is based on 1.13 and fixes the bugs 1.14 fixed withou breaking |
|            | lots of other things.                                          |
| 2003-02-15 | The  selected node can now be made visible even when  the tree |
|            | control  loses focus.  It uses a new class  declaration in the |
|            | css file '.webfx-tree-item a.selected-inactive', by default it |
|            | puts a light-gray rectangle around the selected node.          |
| 2003-03-16 | Adding target support after lots of lobbying...                |
|-----------------------------------------------------------------------------|
| Created 2000-12-11 | All changes are in the log above. | Updated 2003-03-16 |
\----------------------------------------------------------------------------*/

var webFXTreeConfig = {		
	rootIcon        : this.imagePath + '/foldericon.png',
	openRootIcon    : this.imagePath + '/openfoldericon.png',
	folderIcon      :  this.imagePath + '/foldericon.png',
	openFolderIcon  :this.imagePath + '/openfoldericon.png',
	fileIcon        : this.imagePath + '/file.png',
	iIcon           : this.imagePath + '/I.png',
	lIcon           : this.imagePath + '/L.png',
	lMinusIcon      : this.imagePath + '/Lminus.png',
	lPlusIcon       : this.imagePath + '/Lplus.png',
	tIcon           : this.imagePath + '/T.png',
	tMinusIcon   : this.imagePath + '/Tminus.png',
	tPlusIcon       : this.imagePath + '/Tplus.png',
	blankIcon       : this.imagePath + '/blank.png',
	defaultText     : 'Tree Item',
	defaultAction   : 'javascript:void(0);',
	defaultBehavior : 'classic',
	usePersistence	: true,
    disableColor : "#9B9B9B",   // luohc [add] 2004-12-1. \u4e0d\u53ef\u7528\u65f6\u7684\u6587\u672c\u989c\u8272\u3002
	defaultColor : "black",   // luohc [add] 2006-4-30. \u8282\u70b9\u7684\u6587\u672c\u989c\u8272\u3002
	cascadeCheck: true,    // luohc. \u5b9a\u4e49checkbox\u6811\u662f\u5426\u7ea7\u8054\u9009\u62e9\u3002 2005.02.15
	//imagePath : "js/images",  // luohc. \u56fe\u7247\u8def\u5f84.	2005.02.15  // \u8fd9\u4e2a\u5c5e\u6027\u6709\u70b9\u95ee\u9898\uff0c\u8bf7\u4e0d\u8981\u4f7f\u7528. \u53c2\u89c1 setImagePath \u65b9\u6cd5.	
	setImagePath: function(path){  // \u8bbe\u7f6e\u56fe\u7247\u8def\u5f84\u3002\u76f4\u63a5\u8bbe\u7f6e imagePath \u5c5e\u6027\u4e0d\u8d77\u4f5c\u7528\u3002
		if(path == null) path = "js/images/";
		if(path.charAt(path.length-1) != '/') path += "/";
        //this.imagePath = path;
		this.rootIcon = path + "foldericon.png";
		this.openRootIcon = path + 'openfoldericon.png';
		this.folderIcon     =  path + 'foldericon.png';
		this.openFolderIcon  = path + 'openfoldericon.png';
		this.fileIcon       = path + 'file.png';
		this.iIcon           = path + 'I.png';
		this.lIcon           = path + 'L.png';
		this.lMinusIcon   =  path + 'Lminus.png';
		this.lPlusIcon     = path + 'Lplus.png';
		this.tIcon          = path + 'T.png';
		this.tMinusIcon  = path + 'Tminus.png';
		this.tPlusIcon     = path + 'Tplus.png';
		this.blankIcon     = path + 'blank.png';
	}	
	
};

var webFXTreeHandler = {
	idCounter : 0,
	idPrefix  : "webfx-tree-object-apollo-",
	all       : {},
	behavior  : null,
	selected  : null,
	onSelect  : null, /* should be part of tree, not handler */
	getId     : function() { return this.idPrefix + this.idCounter++; },
	toggle    : function (oItem) { this.all[oItem.id.replace('-plus','')].toggle(); },
	select    : function (oItem) { this.all[oItem.id.replace('-icon','')].select(); },
	focus     : function (oItem) { this.all[oItem.id.replace('-anchor','')].focus(); },
	blur      : function (oItem) { this.all[oItem.id.replace('-anchor','')].blur(); },
	keydown   : function (oItem, e) { return this.all[oItem.id].keydown(e.keyCode); },
	cookies   : new WebFXCookie(),
	insertHTMLBeforeEnd	:	function (oElement, sHTML) {
		if (oElement.insertAdjacentHTML != null) {
			oElement.insertAdjacentHTML("BeforeEnd", sHTML)
			return;
		}
		var df;	// DocumentFragment
		var r = oElement.ownerDocument.createRange();
		r.selectNodeContents(oElement);
		r.collapse(false);
		df = r.createContextualFragment(sHTML);
		oElement.appendChild(df);
	}
};

/*
 * WebFXCookie class
 */

function WebFXCookie() {
	this.cookieKey = "extreecookie";
	if (document.cookie.length) { 	        
		this.cookies = ' ' +  document.cookie; 
	}	
}

WebFXCookie.prototype.deleteCookie = function(){
	document.cookie = this.cookieKey + "=none";
	document.cookie = this.cookieKey + "=nothing; expires=Thu, 01-Jan-1970 00:00:01 GMT";
}

WebFXCookie.prototype.setCookie = function (key, value) {
	value = key + "=" + value + ";";
	this.cookies = ' ' +  document.cookie; 
	var values = this.getCookieValue(" " + this.cookieKey, this.cookies);
	if(values == null){		
		document.cookie = this.cookieKey + "=" + escape(value);
	}else{
		values = unescape(values);
		var start = values.indexOf(key + "=");
		if(start>=0){
			var end = values.indexOf(";", start);
			values = values.substring(0, start) + values.substring(end+1) + value;
		}else{
			values = values + value;
		}	
		document.cookie = this.cookieKey + "=" + escape(values);
	}
}

WebFXCookie.prototype.getCookie = function(key){
	this.cookies = ' ' +  document.cookie; 
	if(this.cookies == null) return null;
	var values = this.getCookieValue(" " + this.cookieKey, this.cookies);
    
	var value = null;
	if(values != null){
	   value = this.getCookieValue(key, unescape(values));
	}
	return value;
}

// \u5728 "key1=value1;key2=value2;" \u8fd9\u6837\u7684\u952e\u503c\u5bf9\u5b57\u7b26\u4e32\u4e2d\u83b7\u53d6\u67d0\u952e\u503c\u5bf9\u5e94\u7684\u503c\u3002
WebFXCookie.prototype.getCookieValue = function (key, values) {
	if(values == null) return null;
	var newKey = key + "=";
	var start = values.indexOf(newKey);
	if(start == -1) return null;
	start += newKey.length;
	var end = values.indexOf(";", start);
	if(end == -1) end = values.length;
	var value = values.substring(start, end); 
	return value;
}

/*
 * WebFXTreeAbstractNode class
 */

function WebFXTreeAbstractNode(sText, sAction) {
	this.childNodes  = [];
	this.id     = webFXTreeHandler.getId();
	this.text   = sText || webFXTreeConfig.defaultText;
	
	//this.action = sAction || webFXTreeConfig.defaultAction;
    
	if(sAction != null && sAction.length > 0){
		// luohc [add] 2004-12-07. \u5728new\u4e00\u4e2a\u8282\u70b9\u65f6\u53ef\u4ee5\u7701\u7565javascript:\u524d\u7f00\u3002
		var leftBracket = sAction.indexOf("(");   // \u5de6\u62ec\u53f7.
		var rightBracket = sAction.indexOf(")");  // \u53f3\u62ec\u53f7.
		var js = sAction.toLowerCase().indexOf("javascript:");  // \u524d\u7f00 javascript: \u3002
		// \u5982\u679c\u6709\u5de6\u53f3\u62ec\u53f7(\u8bf4\u660e\u662f\u65b9\u6cd5)\uff0c\u4f46\u53c8\u6ca1\u6709javascript:\u524d\u7f00\u3002
		if(leftBracket>0 && rightBracket>leftBracket && js!=0){
			sAction = "javascript:" + sAction;
		}
		this.action = sAction;
	}else{
		this.action = webFXTreeConfig.defaultAction;
	}

	this._last  = false;
	webFXTreeHandler.all[this.id] = this;
}

/*
 * To speed thing up if you're adding multiple nodes at once (after load)
 * use the bNoIdent parameter to prevent automatic re-indentation and call
 * the obj.ident() method manually once all nodes has been added.
 */

WebFXTreeAbstractNode.prototype.add = function (node, bNoIdent) {
	node.parentNode = this;
	this.childNodes[this.childNodes.length] = node;
	var root = this;
	if (this.childNodes.length >= 2) {
		this.childNodes[this.childNodes.length - 2]._last = false;
	}
	while (root.parentNode) { root = root.parentNode; }
	if (root.rendered) {
		if (this.childNodes.length >= 2) {
			document.getElementById(this.childNodes[this.childNodes.length - 2].id + '-plus').src = ((this.childNodes[this.childNodes.length -2].folder)?((this.childNodes[this.childNodes.length -2].open)?webFXTreeConfig.tMinusIcon:webFXTreeConfig.tPlusIcon):webFXTreeConfig.tIcon);
			this.childNodes[this.childNodes.length - 2].plusIcon = webFXTreeConfig.tPlusIcon;
			this.childNodes[this.childNodes.length - 2].minusIcon = webFXTreeConfig.tMinusIcon;
			this.childNodes[this.childNodes.length - 2]._last = false;
		}
		this._last = true;
		var foo = this;
		while (foo.parentNode) {
			for (var i = 0; i < foo.parentNode.childNodes.length; i++) {
				if (foo.id == foo.parentNode.childNodes[i].id) { break; }
			}
			if (i == foo.parentNode.childNodes.length - 1) { foo.parentNode._last = true; }
			else { foo.parentNode._last = false; }
			foo = foo.parentNode;
		}
		webFXTreeHandler.insertHTMLBeforeEnd(document.getElementById(this.id + '-cont'), node.toString());
		if (!this.folder) {
			this.icon = webFXTreeConfig.folderIcon;
			this.openIcon = webFXTreeConfig.openFolderIcon;
		}
		if (!this.folder) { this.folder = true; this.collapse(true); }
		if (!bNoIdent) { this.indent(); }
	}
	return node;
}

WebFXTreeAbstractNode.prototype.toggle = function() {
	if (this.folder) {
		if (this.open) { this.collapse(); }
		else { this.expand(); }
}	}

WebFXTreeAbstractNode.prototype.select = function() {
	document.getElementById(this.id + '-anchor').focus();
}

WebFXTreeAbstractNode.prototype.deSelect = function() {
	document.getElementById(this.id + '-anchor').className = '';
	webFXTreeHandler.selected = null;
}

WebFXTreeAbstractNode.prototype.focus = function() {
	if ((webFXTreeHandler.selected) && (webFXTreeHandler.selected != this)) { webFXTreeHandler.selected.deSelect(); }
	webFXTreeHandler.selected = this;
	if ((this.openIcon) && (webFXTreeHandler.behavior != 'classic')) { document.getElementById(this.id + '-icon').src = this.openIcon; }

	document.getElementById(this.id + '-anchor').className = 'selected';
	try{document.getElementById(this.id + '-anchor').focus();}catch(e){}
	if (webFXTreeHandler.onSelect) { webFXTreeHandler.onSelect(this); }
}

WebFXTreeAbstractNode.prototype.blur = function() {
	if ((this.openIcon) && (webFXTreeHandler.behavior != 'classic')) { document.getElementById(this.id + '-icon').src = this.icon; }
	document.getElementById(this.id + '-anchor').className = 'selected-inactive';
}

WebFXTreeAbstractNode.prototype.doExpand = function() {
	_expand_node(this);		
}

//luohc add 2006-05-12.
function _expand_node(node){
	if (webFXTreeHandler.behavior == 'classic') { document.getElementById(node.id + '-icon').src = node.openIcon; }
	if (node.childNodes.length) {  document.getElementById(node.id + '-cont').style.display = 'block'; }
	node.open = true;
	if (webFXTreeConfig.usePersistence) {
		webFXTreeHandler.cookies.setCookie(node.id.substr(18,node.id.length - 18), '1');
    }
}

WebFXTreeAbstractNode.prototype.doCollapse = function() {
	if (webFXTreeHandler.behavior == 'classic') { document.getElementById(this.id + '-icon').src = this.icon; }
	if (this.childNodes.length) { document.getElementById(this.id + '-cont').style.display = 'none'; }
	this.open = false;
	if (webFXTreeConfig.usePersistence) {
		webFXTreeHandler.cookies.setCookie(this.id.substr(18,this.id.length - 18), '0');
}	}

WebFXTreeAbstractNode.prototype.expandAll = function() {
	this.expandChildren();
	if ((this.folder) && (!this.open)) { this.expand(); }
}

WebFXTreeAbstractNode.prototype.expandChildren = function() {
	for (var i = 0; i < this.childNodes.length; i++) {
		this.childNodes[i].expandAll();
} }

WebFXTreeAbstractNode.prototype.collapseAll = function() {
	this.collapseChildren();
	if ((this.folder) && (this.open)) { this.collapse(true); }
}

WebFXTreeAbstractNode.prototype.collapseChildren = function() {
	for (var i = 0; i < this.childNodes.length; i++) {
		this.childNodes[i].collapseAll();
} }

WebFXTreeAbstractNode.prototype.indent = function(lvl, del, last, level, nodesLeft) {
	/*
	 * Since we only want to modify items one level below ourself,
	 * and since the rightmost indentation position is occupied by
	 * the plus icon we set this to -2
	 */
	if (lvl == null) { lvl = -2; }
	var state = 0;
	for (var i = this.childNodes.length - 1; i >= 0 ; i--) {
		state = this.childNodes[i].indent(lvl + 1, del, last, level);
		if (state) { return; }
	}
	if (del) {
		if ((level >= this._level) && (document.getElementById(this.id + '-plus'))) {
			if (this.folder) {
				document.getElementById(this.id + '-plus').src = (this.open)?webFXTreeConfig.lMinusIcon:webFXTreeConfig.lPlusIcon;
				this.plusIcon = webFXTreeConfig.lPlusIcon;
				this.minusIcon = webFXTreeConfig.lMinusIcon;
			}
			else if (nodesLeft) { document.getElementById(this.id + '-plus').src = webFXTreeConfig.lIcon; }
			return 1;
	}	}
	var foo = document.getElementById(this.id + '-indent-' + lvl);
	if (foo) {
		if ((foo._last) || ((del) && (last))) { foo.src =  webFXTreeConfig.blankIcon; }
		else { foo.src =  webFXTreeConfig.iIcon; }
	}
	return 0;
}

// \u8bbe\u7f6e\u8282\u70b9\u7684id\u3002luohc 2005-11-30.
WebFXTreeAbstractNode.prototype.setId = function (id) {
	this.oid = id;
	webFXTreeHandler.all[id] = this;	
}

// \u83b7\u5f97\u8282\u70b9\u7684id\u3002luohc 2005-11-30.
WebFXTreeAbstractNode.prototype.getId = function () {
	if(this.oid){
	    return this.oid;
	}else{
		return this.id;
	}
}

// \u6839\u636eId\u83b7\u5f97\u8282\u70b9\u5bf9\u8c61\u3002 luohc 2005-11-30.
function getNodeById(id){
	return webFXTreeHandler.all[id];	
}

// \u8bbe\u7f6e\u8282\u70b9\u7684\u540d\u79f0\u3002luohc 2005-7-28.
WebFXTreeAbstractNode.prototype.setText = function (txt) {
	var id = this.id + "-anchor";
	var anchor = document.getElementById(id);
	if(anchor != null){
		anchor.innerText = txt;
	}
}

// \u8bbe\u7f6e\u8282\u70b9\u7684\u989c\u8272\u3002luohc 2005-7-28.
WebFXTreeAbstractNode.prototype.setColor = function (color) {
	var id = this.id + "-anchor";
	var anchor = document.getElementById(id);
	if(anchor != null){
		anchor.style.color = color;
	}
	this.color = color;
}

// \u8bbe\u7f6e\u8282\u70b9\u7684\u989c\u8272\u3002luohc 2005-7-28.
WebFXTreeAbstractNode.prototype.getColor = function (color) {
	if(this.color){
		return this.color;		
	}else if(this._disabled){
		return webFXTreeConfig.disableColor;
	}else{
		return webFXTreeConfig.defaultColor;
	}
}

// luohc.
function getTreeRoot(){
   return tree_root;
}

/*
 * WebFXTree class
 */
var tree_root; // luohc
function WebFXTree(sText, sAction, sBehavior, sIcon, sOpenIcon) {
	this.base = WebFXTreeAbstractNode;
	this.base(sText, sAction);
	this.icon      =  webFXTreeConfig.rootIcon;
	this.openIcon  =  webFXTreeConfig.openRootIcon;
	if(sIcon){
		this.icon = sIcon;
		this.openIcon  = sIcon;
	}
	if(sOpenIcon){
		this.openIcon = sOpenIcon;
	}
	/* Defaults to open */
	if (webFXTreeConfig.usePersistence) {
		this.open  = (webFXTreeHandler.cookies.getCookie(this.id.substr(18,this.id.length - 18)) == '0')?false:true;
	} else { this.open  = true; }
	this.folder    = true;
	this.rendered  = false;
	this.onSelect  = null;
	if (!webFXTreeHandler.behavior) {  webFXTreeHandler.behavior = sBehavior || webFXTreeConfig.defaultBehavior; }

	tree_root = this; // luohc 2004-7-30.
}

WebFXTree.prototype = new WebFXTreeAbstractNode;

WebFXTree.prototype.setBehavior = function (sBehavior) {
	webFXTreeHandler.behavior =  sBehavior;
};

WebFXTree.prototype.getBehavior = function (sBehavior) {
	return webFXTreeHandler.behavior;
};

WebFXTree.prototype.getSelected = function() {
	if (webFXTreeHandler.selected) { return webFXTreeHandler.selected; }
	else { return null; }
}

WebFXTree.prototype.remove = function() { }

WebFXTree.prototype.expand = function() {
	this.doExpand();
}

WebFXTree.prototype.collapse = function(b) {
	if (!b) { this.focus(); }
	this.doCollapse();
}

WebFXTree.prototype.getFirst = function() {
	return null;
}

WebFXTree.prototype.getLast = function() {
	return null;
}

WebFXTree.prototype.getNextSibling = function() {
	return null;
}

WebFXTree.prototype.getPreviousSibling = function() {
	return null;
}

WebFXTree.prototype.keydown = function(key) {
	if (key == 39) {
		if (!this.open) { this.expand(); }
		else if (this.childNodes.length) { this.childNodes[0].select(); }
		return false;
	}
	if (key == 37) { this.collapse(); return false; }
	if ((key == 40) && (this.open) && (this.childNodes.length)) { this.childNodes[0].select(); return false; }
	return true;
}

WebFXTree.prototype.toString = function() {
	var str = "<div id=\"" + this.id + "\" ondblclick=\"webFXTreeHandler.toggle(this);\" class=\"webfx-tree-item\" onkeydown=\"return webFXTreeHandler.keydown(this, event)\">" +
		"<img id=\"" + this.id + "-icon\" class=\"webfx-tree-icon\" src=\"" + ((webFXTreeHandler.behavior == 'classic' && this.open)?this.openIcon:this.icon) + "\" onclick=\"webFXTreeHandler.select(this);\">" +
		"<a href=\"" + this.action + "\" id=\"" + this.id + "-anchor\" onfocus=\"webFXTreeHandler.focus(this);\" onblur=\"webFXTreeHandler.blur(this);\"" +
		(this.target ? " target=\"" + this.target + "\"" : "") + " style='color:" + this.getColor() + "'" +
		">" + this.text + "</a></div>" +
		"<div id=\"" + this.id + "-cont\" class=\"webfx-tree-container\" style=\"display: " + ((this.open)?'block':'none') + ";\">";
	var sb = [];
	for (var i = 0; i < this.childNodes.length; i++) {
		sb[i] = this.childNodes[i].toString(i, this.childNodes.length);
	}
	this.rendered = true;
	return str + sb.join("") + "</div>";
};

/*
 * WebFXTreeItem class
 */

function WebFXTreeItem(sText, sAction, eParent, sIcon, sOpenIcon) {
	this.base = WebFXTreeAbstractNode;
	this.base(sText, sAction);
	/* Defaults to close */
	if (webFXTreeConfig.usePersistence) {
		this.open = (webFXTreeHandler.cookies.getCookie(this.id.substr(18,this.id.length - 18)) == '1')?true:false;
	} else { this.open = false; }

	if (sIcon) { 
		this.icon = sIcon; 
		this.openIcon = sIcon; 
	}
	if (sOpenIcon) { this.openIcon = sOpenIcon; }
	if (eParent) { eParent.add(this); }
}

WebFXTreeItem.prototype = new WebFXTreeAbstractNode;

WebFXTreeItem.prototype.remove = function() {
	var iconSrc = document.getElementById(this.id + '-plus').src;
	var parentNode = this.parentNode;
	var prevSibling = this.getPreviousSibling(true);
	var nextSibling = this.getNextSibling(true);
	var folder = this.parentNode.folder;
	var last = ((nextSibling) && (nextSibling.parentNode) && (nextSibling.parentNode.id == parentNode.id))?false:true;
	//this.getPreviousSibling().focus();  // luohc 2006-05-15 \u6ce8\u91ca\u6389.
	this._remove();
	if (parentNode.childNodes.length == 0) {
		document.getElementById(parentNode.id + '-cont').style.display = 'none';
		parentNode.doCollapse();
		parentNode.folder = false;
		parentNode.open = false;
	}
	if (!nextSibling || last) { parentNode.indent(null, true, last, this._level, parentNode.childNodes.length); }
	if ((prevSibling == parentNode) && !(parentNode.childNodes.length)) {
		prevSibling.folder = false;
		prevSibling.open = false;
		iconSrc = document.getElementById(prevSibling.id + '-plus').src;
		iconSrc = iconSrc.replace('minus', '').replace('plus', '');
		document.getElementById(prevSibling.id + '-plus').src = iconSrc;
		//document.getElementById(prevSibling.id + '-icon').src = webFXTreeConfig.fileIcon; // luohc.
	}
	if (document.getElementById(prevSibling.id + '-plus')) {
		if (parentNode == prevSibling.parentNode) {
			iconSrc = iconSrc.replace('minus', '').replace('plus', '');
			document.getElementById(prevSibling.id + '-plus').src = iconSrc;
}	}	}

WebFXTreeItem.prototype._remove = function() {
	for (var i = this.childNodes.length - 1; i >= 0; i--) {
		this.childNodes[i]._remove();
 	}
	for (var i = 0; i < this.parentNode.childNodes.length; i++) {
		if (this == this.parentNode.childNodes[i]) {
			for (var j = i; j < this.parentNode.childNodes.length; j++) {
				this.parentNode.childNodes[j] = this.parentNode.childNodes[j+1];
			}
			this.parentNode.childNodes.length -= 1;
			if (i + 1 == this.parentNode.childNodes.length) { this.parentNode._last = true; }
			break;
	}	}
	webFXTreeHandler.all[this.id] = null;
	var tmp = document.getElementById(this.id);
	if (tmp) { tmp.parentNode.removeChild(tmp); }
	tmp = document.getElementById(this.id + '-cont');
	if (tmp) { tmp.parentNode.removeChild(tmp); }
}


WebFXTreeItem.prototype.expand = function() {
	// \u589e\u52a0\u5c55\u5f00\u7236\u8282\u70b9\u3002 luohc 2006-05-12.	
	if(this.parentNode != null && this.parentNode.open == false) this.parentNode.expand();
    if(this.childNodes.length){
		this.doExpand();
		document.getElementById(this.id + '-plus').src = this.minusIcon;
	}
}

WebFXTreeItem.prototype.collapse = function(b) {
	if (!b) { this.focus(); }
	this.doCollapse();
	document.getElementById(this.id + '-plus').src = this.plusIcon;
}

WebFXTreeItem.prototype.getFirst = function() {
	return this.childNodes[0];
}

WebFXTreeItem.prototype.getLast = function() {
	if (this.childNodes[this.childNodes.length - 1].open) { return this.childNodes[this.childNodes.length - 1].getLast(); }
	else { return this.childNodes[this.childNodes.length - 1]; }
}

WebFXTreeItem.prototype.getNextSibling = function() {
	for (var i = 0; i < this.parentNode.childNodes.length; i++) {
		if (this == this.parentNode.childNodes[i]) { break; }
	}
	if (++i == this.parentNode.childNodes.length) { return this.parentNode.getNextSibling(); }
	else { return this.parentNode.childNodes[i]; }
}

WebFXTreeItem.prototype.getPreviousSibling = function(b) {
	for (var i = 0; i < this.parentNode.childNodes.length; i++) {
		if (this == this.parentNode.childNodes[i]) { break; }
	}
	if (i == 0) { return this.parentNode; }
	else {
		if ((this.parentNode.childNodes[--i].open) || (b && this.parentNode.childNodes[i].folder)) { return this.parentNode.childNodes[i].getLast(); }
		else { return this.parentNode.childNodes[i]; }
} }

WebFXTreeItem.prototype.keydown = function(key) {
	if ((key == 39) && (this.folder)) {
		if (!this.open) { this.expand(); }
		else { this.getFirst().select(); }
		return false;
	}
	else if (key == 37) {
		if (this.open) { this.collapse(); }
		else { this.parentNode.select(); }
		return false;
	}
	else if (key == 40) {
		if (this.open) { this.getFirst().select(); }
		else {
			var sib = this.getNextSibling();
			if (sib) { sib.select(); }
		}
		return false;
	}
	else if (key == 38) { this.getPreviousSibling().select(); return false; }
	return true;
}

WebFXTreeItem.prototype.toString = function (nItem, nItemCount) {
	var foo = this.parentNode;
	var indent = '';
	if (nItem + 1 == nItemCount) { this.parentNode._last = true; }
	var i = 0;
	while (foo.parentNode) {
		foo = foo.parentNode;
		indent = "<img id=\"" + this.id + "-indent-" + i + "\" src=\"" 
			+ ((foo._last)?webFXTreeConfig.blankIcon:webFXTreeConfig.iIcon) + "\">" + indent;
		i++;
	}
	this._level = i;
	if (this.childNodes.length) { this.folder = 1; }
	else { this.open = false; }
	if ((this.folder) || (webFXTreeHandler.behavior != 'classic')) {
		if (!this.icon) { this.icon = webFXTreeConfig.folderIcon; }
		if (!this.openIcon) { this.openIcon = webFXTreeConfig.openFolderIcon; }
	} else if (!this.icon) { this.icon = webFXTreeConfig.fileIcon; }

	if(!this.openIcon){ this.openIcon = webFXTreeConfig.fileIcon; }

	var label = this.text.replace(/</g, '&lt;').replace(/>/g, '&gt;');
	var str = "<div id=\"" + this.id + "\" ondblclick=\"webFXTreeHandler.toggle(this);\" class=\"webfx-tree-item\" onkeydown=\"return webFXTreeHandler.keydown(this, event)\">" +
		indent +
		"<img id=\"" + this.id + "-plus\" src=\"" + ((this.folder)?((this.open)?((this.parentNode._last)?webFXTreeConfig.lMinusIcon:webFXTreeConfig.tMinusIcon):((this.parentNode._last)?webFXTreeConfig.lPlusIcon:webFXTreeConfig.tPlusIcon)):((this.parentNode._last)?webFXTreeConfig.lIcon:webFXTreeConfig.tIcon)) + "\" onclick=\"webFXTreeHandler.toggle(this);\">" +
		"<img id=\"" + this.id + "-icon\" class=\"webfx-tree-icon\" src=\"" + ((webFXTreeHandler.behavior == 'classic' && this.open)?this.openIcon:this.icon) + "\" onclick=\"webFXTreeHandler.select(this);\">" +
		"<a href=\"" + this.action + "\" id=\"" + this.id + "-anchor\" onfocus=\"webFXTreeHandler.focus(this);\" onblur=\"webFXTreeHandler.blur(this);\"" +
		(this.target ? " target=\"" + this.target + "\"" : "") + " style='color:" + this.getColor() + "'" +
		">" + label + "</a></div>" +
		"<div id=\"" + this.id + "-cont\" class=\"webfx-tree-container\" style=\"display: " + ((this.open)?'block':'none') + ";\">";
	var sb = [];
	for (var i = 0; i < this.childNodes.length; i++) {
		sb[i] = this.childNodes[i].toString(i,this.childNodes.length);
	}
	this.plusIcon = ((this.parentNode._last)?webFXTreeConfig.lPlusIcon:webFXTreeConfig.tPlusIcon);
	this.minusIcon = ((this.parentNode._last)?webFXTreeConfig.lMinusIcon:webFXTreeConfig.tMinusIcon);
	return str + sb.join("") + "</div>";
}

// 2006-05-12 \u65b0\u589e\u8282\u70b9\u79fb\u52a8\u65b9\u6cd5. luohc.
WebFXTreeItem.prototype.move = function (pNode, index) {	
	/*
	var _children = [];
	if(index != null && index >=0 && index<pNode.childNodes.length){
		for(var i=index; i<pNode.childNodes.length; i++){
			var n = pNode.childNodes[i];
			n = _moveChildBeforeRemove(n);
			_children[_children.length] = n;
			n.remove();
		}		
	} */

    var node = _moveChildBeforeRemove(this);
	this.remove();
	_addChildTree(pNode, node);
    /*
	for(var i=0; i<_children.length; i++){
		_addChildTree(pNode, _children[i]);
	}*/
};

function _moveChildBeforeRemove(node){
	if(node.childNodes.length){
		node.children = [];
		for(var i=0; i<node.childNodes.length; i++){
			node.children[node.children.length] = node.childNodes[i];
			_moveChildBeforeRemove(node.childNodes[i]);
		}
	}
	return node;
}

function _addChildTree(pNode, node){
	webFXTreeHandler.all[node.id] = node;
	pNode.add(node);
	if(node.children){
		for(var i=0; i<node.children.length; i++){
			_addChildTree(node, node.children[i]);
		}
	}
}
