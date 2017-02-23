function GetRandomNum(Min,Max)
{   
	var Range = Max - Min;   
	var Rand = Math.random();   
	return(Min + Math.round(Rand * Range));   
}
var rand = GetRandomNum(1,4);
function getDesc(r, username){
	if (r == 0){
		return share0Desc[rand].title.replace("xxx", username);
	} else if (r == 1){
		return share1Desc[rand].title.replace("xxx", username);
	}  else if (r == 2){
		return share2Desc[rand].title.replace("xxx", username);
	}  else if (r == 3){
		return share3Desc[rand].title.replace("xxx", username);
	}  else if (r == 4){
		return share4Desc[rand].title.replace("xxx", username);
	}  else if (r == 5){
		return share5Desc[rand].title.replace("xxx", username);
	}  else if (r == 6){
		return share6Desc[rand].title.replace("xxx", username);
	}
}
function getCartoon(r){
	if (r == 0 ){
		return $(".r_cartoon").attr("src", "img/resultpage/xuebaCartoon.png");
	} else if (r == 1){
		return $(".r_cartoon").attr("src", "img/resultpage/xuexianCartoon.png");
	} else if (r == 2){
		return $(".r_cartoon").attr("src", "img/resultpage/xuebiaoCartoon.png");
	} else if (r == 3){
		return $(".r_cartoon").attr("src", "img/resultpage/xueruoCartoon.png");
	} else if (r == 4){
		return $(".r_cartoon").attr("src", "img/resultpage/xueminCartoon.png");
	} else if (r == 5){
		return $(".r_cartoon").attr("src", "img/resultpage/xueshenCartoon.png");
	} else if (r == 6){
		return $(".r_cartoon").attr("src", "img/resultpage/xueshenCartoon.png");
	}
}
function getShareFriend(r, username){
	if (r == 0){
		return share0Friend[rand];
	} else if (r == 1){
		return share1Friend[rand];
	}  else if (r == 2){
		return share2Friend[rand];
	}  else if (r == 3){
		return share3Friend[rand];
	}  else if (r == 4){
		return share4Friend[rand];
	}  else if (r == 5){
		return share5Friend[rand];
	}  else if (r == 6){
		return share6Friend[rand];
	}
}
function getShare(r){
	if (r == 0){
		return share0Share[rand];
	} else if (r == 1){
		return share1Share[rand];
	}  else if (r == 2){
		return share2Share[rand];
	}  else if (r == 3){
		return share3Share[rand];
	}  else if (r == 4){
		return share4Share[rand];
	}  else if (r == 5){
		return share5Share[rand];
	}  else if (r == 6){
		return share6Share[rand];
	}
}
var share6Desc = {
		1:{
		title:"学神颜值高，<br>带我上自习。<br>学神兴趣广，<br>助我脱倒一。<br><br><img src='img/resultpage/sName.png' alt='' style='width: 1.1806rem;height: 0.6111rem;display: inline'/><span style='font-size: 0.5833rem;color: #01cd9a;margin-left: 0.1rem;word-spacing: -0.3472rem;'>xxx</span><span style='margin-left: 0.1rem'>和学霸之间隔着一条街的学婊!</span>"},//word-spacing: -0.3472rem;letter-spacing:-0.3472rem;
		2:{
		 title:"不听课、不做题，<br>天一黑就洗洗睡。<br>考前听歌看小说，<br>成绩照样前三名<br><br>原来<span style='font-size: 0.5833rem;color: #01cd9a;margin-left: 0.1rem;word-spacing: -0.3472rem;'>xxx</span><span style='margin-left: 0.1rem'>就是神一样的存在！</span>"},
		3:{
		 title:"兴趣广泛是个逗B，<br>有颜任性帅哭丑B。<br>老师爱，同学捧。<br>对学神来说，题有两种：<br>会做的，出错的。<br><span style='font-size: 0.5833rem;color: #01cd9a;word-spacing: -0.3472rem;'>xxx</span><span style='margin-left: 0.1rem'>是一代</span><img src='img/resultpage/sName.png' alt='' style='width: 1.1806rem;height: 0.6111rem;margin-left:0.1rem;margin-right:0.1rem;display: inline'/>，任性到停不下来。"},
		4:{
		 title:"兴趣广泛有颜任性，<br>从不学习也能秒杀一片，<br>因此成为老师的宠儿，<br>全民的公敌<br><br><img src='img/resultpage/sName.png' alt='' style='width: 1.1806rem;height: 0.6111rem;display: inline'/>，<span>说的就是</span><span style='font-size: 0.5833rem;color: #01cd9a;margin-left: 0.1rem;margin-right:0.1rem;word-spacing: -0.3472rem;'>xxx</span>。"},
		}
var share6Friend = {
		1:{
		 title:"学神xxx和学霸之间隔着一条街的学婊!——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/s.jpg"},
		2:{
		 title:"原来xxx就是神一样的存在！——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/s.jpg"},
		3:{
		 title:"xxx是一代学神，任性到停不下来。——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/s.jpg"},
		4:{
		 title:"学神，说的就是xxx。——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/s.jpg"}
		};

var share6Share = {
		1:{
		 title:"脸红心跳，一zuō到底",
		 desc:"学神xxx和学霸之间隔着一条街的学婊!",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/s.jpg"},
		2:{
		 title:"脸红心跳，一zuō到底",
		 desc:"原来xxx就是神一样的存在！",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/s.jpg"},
		3:{
		 title:"脸红心跳，一zuō到底",
		 desc:"xxx是一代学神，任性到停不下来。",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/s.jpg"},
		4:{
		 title:"脸红心跳，一zuō到底",
		 desc:"学神，说的就是xxx。",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/s.jpg"}
		};

var share5Desc = {
		1:{
		title:"你以为笔记抄得很清楚，<br>谁知重点是整本书！<br><br>你不会相信<span style='font-size: 0.5833rem;color: #a79eab;margin-left: 0.1rem;word-spacing: -0.3472rem;'>xxx</span><span style='margin-left: 0.1rem'>作为一个</span><img src='img/resultpage/zName.png' alt='' style='width: 1.1806rem;height: 0.6111rem;display: inline;margin-right: 0.1rem;margin-left: 0.1rem'/>的痛苦，那是早起占座一个人的孤独。"},
		2:{
		 title:"学渣复习全靠背，<br>成绩一出就给跪。<br>看一句，忘三行，<br>一夜复习两茫茫 <br><br><span style='font-size: 0.5833rem;color: #a79eab;margin-left: 0.1rem;margin-right: 0.1rem;word-spacing: -0.3472rem;'>xxx</span>,今天你渣了吗？"},
		3:{
		 title:"学渣苦,学渣累，<br>学渣考前不能睡。<br>学渣复习全靠背，<br>精神不好脑子废。<br>选择填空蒙不对，<br>简答论述全给跪。<br><img src='img/resultpage/zName.png' alt='' style='width: 1.1806rem;height: 0.6111rem;display: inline'/><span style='font-size: 0.5833rem;color: #a79eab;margin-left: 0.1rem;word-spacing: -0.3472rem;'>xxx</span><span style='margin-left: 0.1rem'>的学习生涯，来感受下。</span>"},
		4:{
		 title:"半学期在后排睡觉，<br>临考试抱学霸大腿。<br>不是在补考的路上，<br>就是在重修的路上。<br>彪悍的人生不需要解释<br><br><img src='img/resultpage/zName.png' alt='' style='width: 1.1806rem;height: 0.6111rem;display: inline'/><span style='font-size: 0.5833rem;color: #a79eab;margin-left: 0.1rem;word-spacing: -0.3472rem;'>xxx</span><span style='margin-left: 0.1rem'>的学习生涯，来感受下。</span> "},
		}
var share5Friend = {
		1:{
		 title:"你不会相信xxx作为一个学渣的痛苦，那是早起占座一个人的孤独。一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/z.jpg"},
		2:{
		 title:"xxx，今天你渣了吗？——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/z.jpg"},
		3:{
		 title:"学渣xxx的学习生涯，来感受下。——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/z.jpg"},
		4:{
		 title:"学渣xxx的学习生涯，来感受下。 ——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/z.jpg"}
		};

var share5Share = {
		1:{
		 title:"脸红心跳，一zuō到底",
		 desc:"你不会相信xxx作为一个学渣的痛苦，那是早起占座一个人的孤独。",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/z.jpg"},
		2:{
		 title:"脸红心跳，一zuō到底",
		 desc:"xxx，今天你渣了吗？",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/z.jpg"},
		3:{
		 title:"脸红心跳，一zuō到底",
		 desc:"学渣xxx的学习生涯，来感受下。",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/z.jpg"},
		4:{
		 title:"脸红心跳，一zuō到底",
		 desc:"学渣xxx的学习生涯，来感受下。 ",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/z.jpg"}
		};


var share4Desc = {
		1:{
		title:"学民是你，<br>学民是我，<br>学习是你我血泪交织的生活，<br>我们都是万千中等生中的一个 <br><br><span style='font-size: 0.5833rem;color: #e5664f;word-spacing: -0.3472rem;margin-right: 0.1rem'>xxx</span>你是<img src='img/resultpage/mName.png' alt='' style='width: 1.1806rem;height: 0.6111rem;margin-left:0.1rem;margin-right:0.1rem;display: inline'/>的骄傲~"},
		2:{
		 title:"不上不下，不前不后，<br>成绩单上，掐头去尾<br>精疲力竭没效果，<br><br>中等生就是<span style='font-size: 0.5833rem;;color: #e5664f;word-spacing: -0.3472rem;margin-left: 0.1rem'>xxx</span>了！"},
		3:{
		 title:"不拔尖不掉队，<br>膜拜学霸，鄙视学渣。<br>学民宣言：做个中等生“挺美”！<br><br><span style='font-size: 0.5833rem;;color: #e5664f;word-spacing: -0.3472rem;margin-right: 0.1rem'>xxx</span>快去捍卫<img src='img/resultpage/mName.png' alt='' style='width: 1.1806rem;height: 0.6111rem;margin-left:0.1rem;margin-right:0.1rem;display: inline'/>的尊严吧！"},
		4:{
		 title:"听课要看心情，<br>考前绝对用功。<br>上有学霸下有学渣，<br>永无出头之日。<br>不管学不学，<br>成绩总在中游<br><span>比上不足，比下有余，</span><span style='font-size: 0.5833rem;color: #e5664f;word-spacing: -0.3472rem;margin-right: 0.1rem'>xxx</span><span>是普通学生。</span> "},
		}
var share4Friend = {
		1:{
		 title:"xxx你是学民的骄傲~ ——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/m.jpg"},
		2:{
		 title:"中等生就是xxx了！——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/m.jpg"},
		3:{
		 title:"xxx快去捍卫学民的尊严吧！。——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/m.jpg"},
		4:{
		 title:"比上不足，比下有余，xxx是普通学生。xxx完爆绿茶婊——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/m.jpg"}
		};

var share4Share = {
		1:{
		 title:"脸红心跳，一zuō到底",
		 desc:"xxx你是学民的骄傲~ ",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/m.jpg"},
		2:{
		 title:"脸红心跳，一zuō到底",
		 desc:"中等生就是xxx了！",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/m.jpg"},
		3:{
		 title:"脸红心跳，一zuō到底",
		 desc:"xxx快去捍卫学民的尊严吧！",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/m.jpg"},
		4:{
		 title:"脸红心跳，一zuō到底",
		 desc:"比上不足，比下有余，xxx是普通学生。",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/m.jpg"}
		};


var share3Desc = {
		1:{
		title:"拿到试卷两眼黑，<br>考完答案都不对；<br>与其刷夜拼命背，<br>不如考前洗洗睡 <br><br><img src='img/resultpage/rName.png' alt='' style='width: 1.1806rem;height: 0.6111rem;display: inline'/><span style='font-size: 0.5833rem;color: #ce7c20;margin-left: 0.1rem;word-spacing: -0.3472rem;'>xxx</span>,还是洗洗睡吧！"},
		2:{
		 title:"天天做题然并卵，<br>时间一长就忘却。<br>精疲力竭没效果，<br>学弱的世界你不懂！<br><br><span style='font-size: 0.5833rem;color: #ce7c20;word-spacing: -0.3472rem;'>xxx</span><span style='margin-left: 0.1rem;margin-right: 0.1rem'>是</span><img src='img/resultpage/rName.png' alt='' style='width: 1.1806rem;height: 0.6111rem;display: inline'/>，太准了！"},
		3:{
		 title:"体弱堪比林黛玉，<br>一学就虚了；<br>点灯熬油刷作业，<br>还是不及格。<br>对学弱来说，世间皆学霸！<br><span style='font-size: 0.5833rem;color: #ce7c20;word-spacing: -0.3472rem;'>xxx</span><span style='margin-left: 0.1rem;margin-right: 0.1rem;'>是</span><img src='img/resultpage/rName.png' alt='' style='width: 1.1806rem;height: 0.6111rem;display: inline'/>，还是多喝六个核桃吧。"},
		4:{
		 title:"没什么可说的了，<br>这不是多喝六个核桃就能解决的事儿！<br><br><span style='font-size: 0.5833rem;color: #ce7c20;word-spacing: -0.3472rem;'>xxx</span><img src='img/resultpage/rName.png' alt='' style='width: 1.1806rem;height: 0.6111rem;margin-left:0.1rem;display: inline'/><span style='margin-left: 0.1rem'>的痛，有谁能懂。</span>"},
		}
var share3Friend = {
		1:{
		 title:"学弱xxx,还是洗洗睡吧！——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/r.jpg"},
		2:{
		 title:"xxx是学弱，太准了！——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/r.jpg"},
		3:{
		 title:"xxx是学弱，还是多喝六个核桃吧。——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/r.jpg"},
		4:{
		 title:"xxx学弱的痛，有谁能懂。xxx完爆绿茶婊——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/r.jpg"}
		};

var share3Share = {
		1:{
		 title:"脸红心跳，——脸红心跳，一zuō到底",
		 desc:"学弱xxx,还是洗洗睡吧！",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/r.jpg"},
		2:{
		 title:"脸红心跳，一zuō到底",
		 desc:"xxx是学弱，太准了！",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/r.jpg"},
		3:{
		 title:"脸红心跳，一zuō到底",
		 desc:"xxx是学弱，还是多喝六个核桃吧。",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/r.jpg"},
		4:{
		 title:"脸红心跳，一zuō到底",
		 desc:"xxx学弱的痛，有谁能懂。",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/r.jpg"}
		};


var share2Desc = {
		1:{
		title:"“我也不会”是标配，<br>梨花带雨惹安慰。<br>学婊说他全不会，<br>考完结果全都对！ <br><br>小<img src='img/resultpage/xbName.png' alt='' style='width: 1.1806rem;height: 0.6111rem;margin-left:0.1rem;margin-right:0.1rem;display: inline'/>舍<span style='font-size: 0.5833rem;color: #78ab07;word-spacing: -0.3472rem;margin-left:0.1rem;margin-right: 0.1rem'>xxx</span>其谁？"},
		2:{
		 title:"考前装作不复习，<br>考后崩溃求安慰。<br>成绩忽上忽下，<br>明明是学霸，<br>偏要装学渣<br><br><span style='font-size: 0.5833rem;color: #78ab07;word-spacing: -0.3472rem;margin-right: 0.1rem'>xxx</span>，还能不能愉快的玩耍啦？"},
		3:{
		 title:"考前陪你逃课看片，<br>考后仰望天空故作忧伤，<br>公布成绩让你大跌眼镜。<br>迷惑性极高，危害深远完爆绿茶婊。<br><br>看热闹不嫌事儿大，<span style='font-size: 0.5833rem;color: #78ab07;word-spacing: -0.3472rem;margin-left:0.1rem;margin-right: 0.1rem'>xxx</span>完爆绿茶婊。"},
		4:{
		 title:"临考前总是装作不复习的样子，<br>下考场必定大哭。<br>你问她考的怎么样？<br>回答经常是“都是瞎蒙的”<br><br>成绩下来，哭晕一帮学渣看热闹不嫌事儿大，<span style='font-size: 0.5833rem;color: #78ab07;word-spacing: -0.3472rem;margin-left:0.1rem;margin-right: 0.1rem'>xxx</span>完爆绿茶婊"},
		}
var share2Friend = {
		1:{
		 title:"小学婊舍xxx其谁？——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/xb.jpg"},
		2:{
		 title:"xxx还能不能愉快的玩耍啦？——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/xb.jpg"},
		3:{
		 title:"看热闹不嫌事儿大，xxx完爆绿茶婊。——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/xb.jpg"},
		4:{
		 title:"成绩下来，哭晕一帮学渣看热闹不嫌事儿大，xxx完爆绿茶婊——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/xb.jpg"}
		};

var share2Share = {
		1:{
		 title:"脸红心跳，一zuō到底",
		 desc:"小学婊舍xxx其谁？",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/xb.jpg"},
		2:{
		 title:"脸红心跳，一zuō到底",
		 desc:"xxx还能不能愉快的玩耍啦？",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/xb.jpg"},
		3:{
		 title:"脸红心跳，一zuō到底",
		 desc:"看热闹不嫌事儿大，xxx完爆绿茶婊。",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/xb.jpg"},
		4:{
		 title:"脸红心跳，一zuō到底",
		 desc:"成绩下来，哭晕一帮学渣看热闹不嫌事儿大，xxx完爆绿茶婊",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/xb.jpg"}
		};

var share1Desc = {
		1:{
		title:"不做凡间试卷，<br>性情飘忽不定，<br>成绩忽上忽下，<br>连老师也要让他三分。<br><br><span style='font-size: 0.5833rem;color: #e5664f;word-spacing: -0.3472rem;margin-right: 0.1rem'>xxx</span>为民服雾！"},
		2:{
		 title:"无忧无虑，<br>无欲无求，<br>学习全凭兴趣<br>如厕自带参考书<br><br><span style='font-size: 0.5833rem;color: #e5664f;word-spacing: -0.3472rem;margin-right: 0.1rem;'>xxx</span>只想做个安静的美人儿/美男子。"},
		3:{
		 title:"学霸的恶梦，<br>学渣的福音。<br>成绩忽上忽下，<br>堪比股市过山车<br><br><span style='font-size: 0.5833rem;color: #e5664f;word-spacing: -0.3472rem;margin-right: 0.1rem'>xxx</span><span style='margin-right: 0.1rem'>成</span><img src='img/resultpage/xName.png' alt='' style='width: 1.1806rem;height: 0.6111rem;margin-right:0.1rem;display: inline'/>了，你相信吗？"},
		4:{
		 title:"学霸的恶梦，<br>学渣的福音。<br>经常一鸣惊人，<br>成为考场黑马。<br>口头禅是“这次又没考好”！<br><br><span style='font-size: 0.5833rem;color: #e5664f;word-spacing: -0.3472rem;margin-right: 0.1rem'>xxx</span><span style='margin-right: 0.1rem'>成</span><img src='img/resultpage/xName.png' alt='' style='width: 1.1806rem;height: 0.6111rem;margin-right:0.1rem;display: inline'/>了，你相信吗？"},
		}
var share1Friend = {
		1:{
		 title:"xxx为民服雾！——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/x.jpg"},
		2:{
		 title:"xxx只想做个安静的美人儿/美男子。——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/x.jpg"},
		3:{
		 title:"xxx成仙了，你相信吗？——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/x.jpg"},
		4:{
		 title:"xxx成仙了，你相信吗？——脸红心跳，一zuō到底",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/x.jpg"}
		};

var share1Share = {
		1:{
		 title:"脸红心跳，一zuō到底",
		 desc:"xxx为民服雾！",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/x.jpg"},
		2:{
		 title:"脸红心跳，一zuō到底",
		 desc:"xxx只想做个安静的美人儿/美男子。",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/x.jpg"},
		3:{
		 title:"脸红心跳，一zuō到底",
		 desc:"xxx成仙了，你相信吗？",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/x.jpg"},
		4:{
		 title:"脸红心跳，一zuō到底",
		 desc:"xxx成仙了，你相信吗？",
		 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/x.jpg"}
		};

var share0Desc = {
				1:{
				title:"愿得此学霸，<br>给我压考题，<br>他得九十八，<br>我得九十七<br><br><span style='font-size: 0.5833rem;word-spacing: -0.3472rem;margin-right:0.1rem;color: #0599d5'>xxx</span>做完这道难题，记得发朋友圈哦~"},
				2:{
				 title:"两耳不闻窗外事，<br>一心只读圣贤书。<br>分秒必争真勤奋，<br>如厕自带参考书<br><br><span style='font-size: 0.5833rem;word-spacing: -0.3472rem;margin-right:0.1rem;color: #0599d5'>xxx</span>别生气，说的就是你！"},
				3:{
				 title:"愿得一学霸，白首不相离。<br>年年好丽友，代代不分离!<br>对学霸来说题目有两种：<br>会做的，超纲的<br><br>快来看看一代<img src='img/resultpage/bName.png' alt='' style='width: 1.1806rem;height: 0.6111rem;margin-left:0.1rem;display: inline'/><span style='font-size: 0.5833rem;word-spacing: -0.3472rem;margin-right:0.1rem;margin-left:0.1rem;color: #0599d5'>xxx</span>的真实写照。"},
				4:{
				 title:"身处教室的黄金位置，<br>多年来与粉笔沫、唾沫为伍，<br>在女神、男神面前也坐怀不乱。<br>说的就是你们啦<br><br>快来看看一代<img src='img/resultpage/bName.png' alt='' style='width: 1.1806rem;height: 0.6111rem;margin-left: 0.1rem;display: inline'/><span style='font-size: 0.5833rem;word-spacing: -0.3472rem;margin-left: 0.1rem;margin-right:0.1rem;color: #0599d5'>xxx</span>的真实写照。"},
				}
var share0Friend = {
				1:{
				 title:"xxx做完这道难题，记得发朋友圈哦~——脸红心跳，一zuō到底",
				 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/b.jpg"},
				2:{
				 title:"xxx别生气，说的就是你！——脸红心跳，一zuō到底",
				 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/b.jpg"},
				3:{
				 title:"快来看看一代学霸xxx的真实写照。——脸红心跳，一zuō到底",
				 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/b.jpg"},
				4:{
				 title:"快来看看一代学霸xxx的真实写照。——脸红心跳，一zuō到底",
				 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/b.jpg"}
				};

var share0Share = {
				1:{
				 title:"脸红心跳，一zuō到底",
				 desc:"xxx做完这道难题，记得发朋友圈哦~",
				 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/b.jpg"},
				2:{
				 title:"脸红心跳，一zuō到底",
				 desc:"xxx别生气，说的就是你！",
				 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/b.jpg"},
				3:{
				 title:"脸红心跳，一zuō到底",
				 desc:"快来看看一代学霸xxx的真实写照。",
				 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/b.jpg"},
				4:{
				 title:"脸红心跳，一zuō到底",
				 desc:"快来看看一代学霸xxx的真实写照。",
				 pic:"http://zuo.shishengclub.com/zuo/img/transmitpage/b.jpg"}
				};