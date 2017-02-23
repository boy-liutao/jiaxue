package com.xuema.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.gson.bean.TemplateData;
import com.gson.bean.UserInfo;
import com.xuema.bean.Activity;
import com.xuema.bean.Banner;
import com.xuema.bean.Chat;
import com.xuema.bean.Classes;
import com.xuema.bean.Coupon;
import com.xuema.bean.Daily;
import com.xuema.bean.GlobalSetting;
import com.xuema.bean.Manager;
import com.xuema.bean.News;
import com.xuema.bean.Orders;
import com.xuema.bean.School;
import com.xuema.bean.Suggestion;
import com.xuema.bean.User;
import com.xuema.exception.XuemaException;
import com.xuema.mapper.IBannerDao;
import com.xuema.mapper.IChatDao;
import com.xuema.mapper.IClassesDao;
import com.xuema.mapper.ICouponDao;
import com.xuema.mapper.IDailyDao;
import com.xuema.mapper.IGlobalSettingDao;
import com.xuema.mapper.IJournalDao;
import com.xuema.mapper.IManagerDao;
import com.xuema.mapper.INewsDao;
import com.xuema.mapper.IOrdersDao;
import com.xuema.mapper.ISchoolDao;
import com.xuema.mapper.ISuggestionDao;
import com.xuema.mapper.IUserDao;
import com.xuema.util.BeanUtil;
import com.xuema.util.DatetimeUtil;
import com.xuema.util.LockUtil;
import com.xuema.util.RandomUtil;
import com.xuema.util.XuemaUtil;
import com.xuema.wechat.MessageUtil;

@Repository(value = "dbService")
@Transactional
public class DBService {
	
	@Autowired
    ISuggestionDao suggestionDao;
	@Autowired
    IUserDao userDao;
	@Autowired
    INewsDao newsDao;
	@Autowired
    IDailyDao dailyDao;
	@Autowired
	ISchoolDao schoolDao;
    @Autowired
    IGlobalSettingDao globalSettingDao;
    @Autowired
    IOrdersDao ordersDao;
    @Autowired
    IChatDao chatDao;
    @Autowired
    IManagerDao managerDao;
    @Autowired
    IJournalDao journalDao;
    @Autowired
    IClassesDao classesDao;
    @Autowired
    IBannerDao bannerDao;
    @Autowired
    ICouponDao couponDao;
	
	public void addSuggestion(Suggestion suggestion) {
		suggestionDao.insert(suggestion);
    }
	public void addUser(User user) {
		userDao.insert(user);
    }
	public User getUser(int id) {
        User u = userDao.select(id);
        return u;
    }
	
	public void updateUser(User user) {
		userDao.update(user);
    }
	public List<News> listNews() {
	    List<News> news = newsDao.list();
	    for (News n : news){
	        if (n.getSid() > 0){
	            School s = schoolDao.select(n.getSid());
	            n.setSchool(s);
	        }
	    }
	    return news;
	}
	
	public News getNews(int id) {
        News u = newsDao.select(id);
        if(u.getSid()>0){
        	School s = schoolDao.select(u.getSid());
            u.setSchool(s);
        }
        return u;
    }
    public User getUserByOpenId(String openId) {
        return userDao.selectByOpenId(openId);
    }
    public User saveUser(UserInfo userInfo) {
        User u = new User();
        u.setOpenid(userInfo.getOpenid());
        u.setImg(userInfo.getHeadimgurl());
        userDao.insert(u);
        return u;
    }
    public void updateUserPhone(int id, String phone, String img) {
        if (StringUtils.isEmpty(phone)){
            //保存历史手机号
            userDao.updateHisPhone(id);
        }
        userDao.updatePhone(id, phone, img);
    }
    public User getUserByPhone(String phone){
        return userDao.getUserByPhone(phone);
    }
    public void updateUserOpenId(int id, String openId, String img) {
        userDao.updateOpenId(id, openId, img);
    }
	public List<Daily> listDaily(int uid) {
        return dailyDao.list(uid);
	}
    public List<School> listSchool() {
        List<School> ss = schoolDao.listAll();
        for (School s : ss){
            List<GlobalSetting> gs = globalSettingDao.listBySid(s.getId());
            s.setSettings(gs);
        }
        return ss;
    }
    public School getSchool(int sid) {
        School s = schoolDao.select(sid);
        List<GlobalSetting> gs = globalSettingDao.listBySid(s.getId());
        s.setSettings(gs);
        return s;
    }
    public GlobalSetting select(int id,String name){
		return globalSettingDao.select(id, name);
    }
    
    public void saveOrder(Orders order) {
        synchronized(LockUtil.sequenceLock) {
            boolean succ = false;
            while (!succ) {
                String oid = RandomUtil.generateCode();
                String day = DatetimeUtil.yyyymmdd();
                String testOid = day+oid;
                Orders tryOrder = ordersDao.select(testOid);
                if (tryOrder == null){
                    succ = true;
                    order.setId(testOid);
                    ordersDao.insert(order);
                }
            }
        }
    }
    
    public void updateOrder(Orders order) {
        synchronized(LockUtil.sequenceLock) {
            ordersDao.update(order);
        }
    }
    
    public List<Orders> listOreder(int id) {
    	List<Orders> list=ordersDao.list(id);
		for (Orders orders : list) {
			if(orders.getSid()>0){
				School s = schoolDao.select(orders.getSid());
				orders.setSchool(s);
				String d=orders.getUpdateTime();
				orders.setUpdateTime(d.substring(0, d.length()-2));
			}

		}
    	return list;
	}
    
    
    public int payOrder(String prepayId) {
        int rows = ordersDao.payOrder(prepayId);
        //加收入流水
        Orders o = getOrderByPayId(prepayId);
        journalDao.insert(o.getId(), o.getPayMoney(), o.getPayWay());
        return rows;
    }
    
    public Orders getOrderByPayId(String payId){
        return ordersDao.selectByPayId(payId);
    }
    public Activity getActivity(int id) {
    	Activity a= newsDao.selectActivity(id);
    	if(a.getSid()>0){
    		School s = schoolDao.select(a.getSid());
    		a.setSchool(s);
    		/*String startTime =a.getStartTime();
    		a.setStartTime(startTime.substring(5,startTime.length()-2));*/
    	}
		return a;
    }
    
    
    public List<Chat> listChat(int uid) {
    	List<Chat> list=chatDao.list(uid);
    	if(list!=null){
    		for (Chat chat : list) {
    			Manager m = (Manager) managerDao.select(chat.getManager());
    			chat.setManagerNickname(m.getNickname());
    			chat.setManagerImg(m.getImg());
    			Chat c=chatDao.selectLastChat(uid, chat.getManager());
    			chat.setContent(c.getContent());
    			chat.setImgs(c.getImgs());
    			chat.setCreateTime(c.getCreateTime());
    			chat.setId(c.getId());
    			chat.setToUser(c.isToUser());
    			chat.setReadStatus(c.getReadStatus());
    		}
    	}
        return list;
	}
    public List<Chat> loadOlderChat(int uid, String manager, int mark, int size){
        List<Chat> list= null;
        if (mark == 0){
            list = chatDao.loadNearest(uid,manager,size);
        } else {
            list = chatDao.loadOlder(uid,manager,mark, size);
        }
    	for (Chat chat : list) {
			Manager m = (Manager) managerDao.select(chat.getManager());
			chat.setManagerImg(m.getImg());
			chatDao.update(chat.getId());
		}
	    return list;
    }   
    public List<Chat> loadNewerChat(int uid, String manager, int mark){
        List<Chat> list=chatDao.loadNewer(uid,manager,mark);
        for (Chat chat : list) {
            Manager m = (Manager) managerDao.select(chat.getManager());
            chat.setManagerImg(m.getImg());
            chatDao.update(chat.getId());
        }
        return list;
    }  
    public List<Chat> loadNewerChat(int uid, String manager, Date afterDate){
        List<Chat> list=chatDao.loadNewerAfterDate(uid,manager,afterDate);
        for (Chat chat : list) {
            Manager m = (Manager) managerDao.select(chat.getManager());
            chat.setManagerImg(m.getImg());
            chatDao.update(chat.getId());
        }
        return list;
    }  
    public List<Chat> loadChatByDate(int uid, String username, Date startDate, Date endDate) throws ParseException {
        if (startDate == null){
            startDate = DatetimeUtil.begining();
        }
        if (endDate == null){
            endDate = DatetimeUtil.tomorrowBegin();
        }
        List<Chat> list = chatDao.loadDuringDate(uid,username,startDate, endDate);
        for (Chat chat : list) {
        	 chatDao.update(chat.getId());
		}
        return list;
    }
    
    public void addChat(Chat chat) throws XuemaException, UnsupportedEncodingException {
    	chatDao.insert(chat);
    	if (chat.isToUser()){
    	    //发送消息给用户
    	    User u = userDao.select(chat.getUid());
    	    String url = XuemaUtil.WX_REDIRECT_HEAD + XuemaUtil.domain_name + XuemaUtil.HOST_ROOT + "/page/myComment1?manager="+chat.getManager() + XuemaUtil.WX_REDIRECT_TAIL;
    	    String msg = "[i家学]您收到一条新信息，<a href=\""+ url +"\">点击查看</a>";
            TemplateData tempData = new TemplateData(u.getOpenid(), "419BzDdigPrQpknUkGCqgcOtWrGu8q9bCfs3vi9g2U8", url);
            tempData.push("first", "[i家学]您收到一条评价信息");
            tempData.push("keyword1", u.getName());
            tempData.push("keyword2", "i家学");
            tempData.push("remark", "i家学感谢您的支持");
            MessageUtil.sendWXMessageForce(u.getOpenid(), msg, tempData);
    	} else {
    	    //发送消息给老师
    	    User u = userDao.select(chat.getUid());
            String url = XuemaUtil.WX_REDIRECT_HEAD + XuemaUtil.domain_name + XuemaUtil.HOST_ROOT + "/t/myCommentTeacher?uid="+chat.getUid()+"&uname="+URLEncoder.encode(u.getName(), "utf-8") + XuemaUtil.WX_REDIRECT_TAIL;
            String msg = "[i家学]您收到一条新信息，<a href=\""+ url +"\">点击查看</a>";
            TemplateData tempData = new TemplateData(u.getOpenid(), "419BzDdigPrQpknUkGCqgcOtWrGu8q9bCfs3vi9g2U8", url);
            tempData.push("first", "[i家学]您收到一条家长信息");
            tempData.push("keyword1", u.getName());
            tempData.push("keyword2", "i家学");
            tempData.push("remark", "i家学感谢您的支持");
            MessageUtil.sendWXMessageForce(u.getOpenid(), msg, tempData);
    	}
    }
    public Map<String, Integer> computeUserRemain(int id) {
        Integer fruitRemain = ordersDao.computeFruitRemain(id);
        Integer dinnerRemain = ordersDao.computeDinnerRemain(id);
        Map<String, Integer> r = new HashMap<String, Integer>();
        r.put("fruitRemain", BeanUtil.parseInt(fruitRemain));
        r.put("dinnerRemain", BeanUtil.parseInt(dinnerRemain));
        return r;
    }
    public Manager getManagerByOpenId(String openId) {
        return managerDao.selectByOpenId(openId);
    }
    public Manager saveManager(UserInfo userInfo) {
        Manager u = new Manager();
        u.setOpenid(userInfo.getOpenid());
        managerDao.insert(u);
        return u;
    }
    public Manager getManagerByPhone(String phone) {
        return managerDao.selectByPhone(phone);
    }
    public void updateManagerOpenId(String phone, String openId) {
        managerDao.updateOpenid(phone, openId);
    }
    public void updateManagerImg(String username, String img) {
        managerDao.updateManagerImg(username, img);
    }
     
    public List<Classes> listClasses(String username, int sid) {
        return classesDao.listByManagerSid(username, sid);
    }
    
    public List<User> listNurseryBefore(String username, String lastOneStr){
        Date lastOne = DatetimeUtil.getTimeFromMinutes(lastOneStr);
        List<User> us = listUserByManagerSidCid(username, -1, -1, null, 2);
        //去掉不接送的
        Iterator<User> iter = us.iterator();
        while (iter.hasNext()){
            User u = iter.next();
            Daily d = u.getDaily();
            if (d.getScheduleTime() == null){
                iter.remove();
            }
        }
        //按接送时间排序
        Collections.sort(us, new Comparator<User>(){

            @Override
            public int compare(User u1, User u2) {
                Date t1 = DatetimeUtil.getTimeFromMinutes(u1.getDaily().getScheduleTime());
                Date t2 = DatetimeUtil.getTimeFromMinutes(u2.getDaily().getScheduleTime());
                return t1.compareTo(t2);
            }
            
        });
        //保留lastOne后面1刻钟内的孩子
        List<User> result = new ArrayList<User>();
        Date nextOne = null;
        for (User u : us){
            if (lastOne == null){
                nextOne =  DatetimeUtil.getTimeFromMinutes(u.getDaily().getScheduleTime());
            } else {
                if (lastOne.compareTo(DatetimeUtil.getTimeFromMinutes(u.getDaily().getScheduleTime())) < 0){
                    nextOne = DatetimeUtil.getTimeFromMinutes(u.getDaily().getScheduleTime());
                }
            }
        }
        if (nextOne == null){
            return result;
        }
        Date split = DatetimeUtil.nextQuarter(nextOne);
        for (User u : us){
            Date t = DatetimeUtil.getTimeFromMinutes(u.getDaily().getScheduleTime());
            if ((lastOne == null || t.compareTo(lastOne)>=0) && t.compareTo(split)<=0){
                result.add(u);
            }
        }
        return result;
    }
    
    //根据老师，学堂，班级和关键词列出用户
    //scope=1为全部用户，scope=2为当天托管用户 
    public List<User> listUserByManagerSidCid(String username, int sid, int cid, String key, int scope) {
        List<User> us = null;
        us = userDao.selectByManager(username, sid, cid, key);
        if (StringUtils.isEmpty(key)){
        } else {
            us = userDao.selectByManagerKey(username, key);
        }
        List<User> onUsers = new ArrayList<User>();
        
        Date today = DatetimeUtil.todayBegin();
        for (User u : us){
        	/*学生聊天的最后一条记录*/
            Chat chat=chatDao.userManagerLastCh(u.getId(), username);
        	u.setLastChat(chat);
            //加入学校和当天daily
            Classes c = classesDao.select(u.getCid());
            School s = schoolDao.select(c.getSid());
            u.setSchool(s);
            Daily d = userDao.selectDaily(u.getId(), today);
            //去掉请假的
            if (scope == 2 && d != null && d.isLeave()){
                continue;
            }
            if (d == null){
                d = new Daily();
                d.setUid(u.getId());
                d.setDate(today);
            }
            //set scheduleTime
            if (StringUtils.isEmpty(d.getScheduleTime())){
                String weekDay = DatetimeUtil.getWeekOfDate(new Date());
                //"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"
                if (weekDay.equals("星期日")){
                    d.setScheduleTime(u.getSunday());
                } else if (weekDay.equals("星期一")){
                    d.setScheduleTime(u.getMonday());
                } else if (weekDay.equals("星期二")){
                    d.setScheduleTime(u.getTuesday());
                } else if (weekDay.equals("星期三")){
                    d.setScheduleTime(u.getWednesday());
                } else if (weekDay.equals("星期四")){
                    d.setScheduleTime(u.getThursday());
                } else if (weekDay.equals("星期五")){
                    d.setScheduleTime(u.getFriday());
                } else if (weekDay.equals("星期六")){
                    d.setScheduleTime(u.getSaturday());
                }
            }
            u.setDaily(d);
            onUsers.add(u);
        }
        return onUsers;
    }
    public boolean addNursery(int uid, String info, String img, String username) throws XuemaException {
        Date today = DatetimeUtil.todayBegin();
        Daily d = userDao.selectDaily(uid, today);
        if (d == null){
            d = new Daily();
            d.setUid(uid);
            d.setDate(today);
            d.setNurseryer(username);
            d.setNurseryInfo(info);
            d.setNurseryStatus(true);
            d.setNurseryImg(img);
            dailyDao.addUserNursery(d);
        } else {
            //如果已经筌到则返回错误
            if (d.isNurseryStatus()){
                return false;
            }
            
            d.setNurseryer(username);
            d.setNurseryInfo(info);
            d.setNurseryStatus(true);
            d.setNurseryImg(img);
            dailyDao.updateUserNursery(d);
        }
        String url = XuemaUtil.WX_REDIRECT_HEAD + XuemaUtil.domain_name + XuemaUtil.HOST_ROOT + "/page/babyArrived" + XuemaUtil.WX_REDIRECT_TAIL;
        //发送消息给用户
        User u = getUser(uid);
        if (!StringUtils.isEmpty(u.getOpenid())){
            School s = getSchool(u.getSid());
            String msg = "[i家学]您收到一条签到信息，<a href=\""+ url +"\">点击查看</a>";
            TemplateData tempData = new TemplateData(u.getOpenid(), "Goselkf91Bg3ZNa_qBt4hGk3zat0a2blKDiWyVEnqRc", url);
            tempData.push("first", "[i家学]您收到一条签到信息");
            tempData.push("keyword1", DatetimeUtil.formatDatetime(new Date()));
            tempData.push("keyword2", s.getName());
            tempData.push("remark", "i家学感谢您的支持");
            MessageUtil.sendWXMessageForce(u.getOpenid(), msg, tempData);
        }
        return true;
    }
    
    public boolean addComment(int uid, String comment, String manager){
        Date today = DatetimeUtil.todayBegin();
        Daily d = dailyDao.today(uid);
        if (d!= null && d.isCommentStatus()){
            return false;
        }
        if (d == null){
            d = new Daily();
            d.setUid(uid);
            d.setDate(today);
            d.setComment(comment);
            d.setCommenter(manager);
            d.setCommentStatus(true);
            dailyDao.insertComment(d);
        } else {
            dailyDao.updateComment(uid, today, comment, manager);
        }
        return true;
    }
    
    public Daily getDaily(int uid, Date date) {
        Daily d = null;
        if (date == null){
            date = DatetimeUtil.todayBegin();
            d = dailyDao.selectLatestDaily(uid, date);
        } else {
            d = dailyDao.selectBeforeDaily(uid, date);
        }
        if (d == null){
            d = new Daily();
            d.setUid(uid);
            d.setDate(date);
        }
        return d;
    }
    public Banner getBanner(int id) {
    	Banner banner = bannerDao.select(id);
        return banner;
    }
	public List<Banner> listBanner() {
        return bannerDao.list();
	}
    public Orders getOrder(String oid) {
    	Orders orders=ordersDao.select(oid);
    	
    	if(orders.getSid()>0){
			School s = schoolDao.select(orders.getSid());
			orders.setSchool(s);
			String d=orders.getUpdateTime();
			orders.setUpdateTime(d.substring(0, d.length()-2));
			User u=userDao.select(orders.getUid());
			orders.setUser(u);
		}
        return orders;
    }
    public void updateUserSchool(int id, int sid) {
        userDao.updateSchool(id, sid);
    }
    public void deleteUser(int id) {
        userDao.delete(id);
    }
    public void saveUser(User u) {
        userDao.insert(u);
    }
    public void setUserSid(int id, int sid) {
        userDao.updateSid(id, sid);
    }
    public List<Coupon> listCoupon(int uid, int cond) {
        return couponDao.list(uid, cond);
    }
    public void frozenCoupon(String cid, String oid) {
        couponDao.frozen(cid, oid);
    }
    public Coupon getCoupon(String cid) {
        return couponDao.get(cid);
    }
    public Coupon getCouponByOid(String oid) {
        return couponDao.getByOid(oid);
    }
    public void consumeCoupon(String id) {
        couponDao.consume(id);
    }
}
    
    
