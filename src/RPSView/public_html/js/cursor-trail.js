if(document.all){
	var ie4up = 1;
}else{
	var ie4up = 0;
}

if(document.getElementById && !document.all){
	var ns6up = 1;
}else{
	var ns6up = 0;
}

for(var f=1; f<6; f++){
    var fa = f*26;
    var fb = f*22;
    document.write("<img id=im"+f+" src='images/image"+f+".gif' style='position:absolute; left: "+fa+"px; top: "+fb+"px;' >");
}

var moving=1;
var onmove = false,imgw=89,imgh=87;
var winW , winH ;
var nht = winH;
var toppos = 0;

if (parseInt(navigator.appVersion)>3) {
 if (navigator.appName=="Netscape") {
  winW = window.innerWidth;
  winH = window.innerHeight;
 }
 if (navigator.appName.indexOf("Microsoft")!=-1) {
  winW = document.body.offsetWidth;
  winH = document.body.offsetHeight;
 }
}
function getScrollXY() {
  var scrOfX = 10, scrOfY = 10;
  if( typeof( window.pageYOffset ) == 'number' ) {
    //Netscape compliant
    scrOfY =window.pageYOffset;
    scrOfX = window.pageXOffset;
  } else if( document.body && ( document.body.scrollLeft || document.body.scrollTop ) ) {
    //DOM compliant
    scrOfY = document.body.scrollTop;
    scrOfX = document.body.scrollLeft;
  } else if( document.documentElement &&
      ( document.documentElement.scrollLeft || document.documentElement.scrollTop ) ) {
    //IE6 standards compliant mode
   scrOfY = document.documentElement.scrollTop;
   scrOfX = document.documentElement.scrollLeft;
  }
  return [ scrOfX, scrOfY ];
}

var timer;

function ranrot()
{

var a = getScrollXY()

if(timer)
{
	clearTimeout(timer);
}
toppos = a[1];
winH = nht+a[1];
timer = setTimeout('ranrot()',1000);
}
 	
ranrot();
function mover(x,y,moving)
{  
	if(moving < 6){
		var ele = document.getElementById("im"+moving);
           if(x<(winW) && y<(winH))
           {
            
	      var afa = (moving-1)*22;
    		var afb = (moving-1)*26;
            var l=x+afa;
		var t=y+afb;
		
            if(l>(winW-109) && t<(winH-109))
            {
            ele.style.left = winW-(imgw+21);
		ele.style.top = y+afb;

		}else if(l<(winW-109) && t>(winH-109))
           {
            ele.style.left =x+afa ;
		ele.style.top = winH-(imgh+4);
            }else if(l>(winW-109) && t>(winH-109))
		{
            ele.style.left =winW-(imgw+21);
		ele.style.top = winH-(imgh+4);

            }else
            {
		ele.style.left = x+afa;
		ele.style.top = y+afb;
            }
		moving = moving+1;
		setTimeout('mover('+x+','+y+','+moving+')',60);
          }
         

	}
}

var timer = setTimeout('mover(200,200,1)',60);

function mouseMoveHandler (evt) {
var b = getScrollXY()

var x1 = document.all ? event.clientX : document.layers ? evt.x : evt.clientX;
var y1 = document.all ? event.clientY : document.layers ? evt.y : evt.clientY;
var x=x1+b[0];
var y=y1+b[1];
setTimeout('mover('+x+','+y+',1)',100);
}


function iecompattest()
{
	if(document.compatMode && document.compatMode!="BackCompat")
	{
		return document.documentElement;
	}else{
		return document.body;
	}
	
}

if (document.layers)
  document.captureEvents(Event.MOUSEMOVE);

if (document.layers || document.all)
  document.onmousemove = mouseMoveHandler;

if (document.addEventListener)
  document.addEventListener('mousemove', mouseMoveHandler, true);
function iecompattest()
{
	if(document.compatMode && document.compatMode!="BackCompat")
	{
		return document.documentElement;
	}else{
		return document.body;
	}
	
}
if (ns6up) {
	winW = window.innerWidth;
	winH = window.innerHeight;
} 
else if (ie4up) {
	winW = iecompattest().clientWidth;
	winH = iecompattest().clientHeight;

}
nht = winH;
