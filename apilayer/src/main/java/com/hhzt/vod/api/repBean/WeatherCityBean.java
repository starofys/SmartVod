package com.hhzt.vod.api.repBean;

/**
 * Created by zengxiaoping on 2018/1/11.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public class WeatherCityBean {

	/**
	 * weatherList : null
	 * weather : {"createTime":1515646801000,"updateTime":null,"createBy":null,"updateBy":null,"id":1,"cityname":"长春","citycode":"101060101","temp":"-23℃～-17℃","currenttemp":"-23℃","weather":"晴","weatherdate":1515600000000,"logourl":"http://192.168.1.182/static/static/images/weather/01qing.png","wd":null,"ws":null,"tenantId":null}
	 */

	private WeatherBean weather;

	public WeatherBean getWeather() {
		return weather;
	}

	public void setWeather(WeatherBean weather) {
		this.weather = weather;
	}

	public static class WeatherBean {
		/**
		 * createTime : 1515646801000
		 * updateTime : null
		 * createBy : null
		 * updateBy : null
		 * id : 1
		 * cityname : 长春
		 * citycode : 101060101
		 * temp : -23℃～-17℃
		 * currenttemp : -23℃
		 * weather : 晴
		 * weatherdate : 1515600000000
		 * logourl : http://192.168.1.182/static/static/images/weather/01qing.png
		 * wd : null
		 * ws : null
		 * tenantId : null
		 */

		private long createTime;
		private Object updateTime;
		private Object createBy;
		private Object updateBy;
		private int id;
		private String cityname;
		private String citycode;
		private String temp;
		private String currenttemp;
		private String weather;
		private long weatherdate;
		private String logourl;
		private Object wd;
		private Object ws;
		private Object tenantId;

		public long getCreateTime() {
			return createTime;
		}

		public void setCreateTime(long createTime) {
			this.createTime = createTime;
		}

		public Object getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(Object updateTime) {
			this.updateTime = updateTime;
		}

		public Object getCreateBy() {
			return createBy;
		}

		public void setCreateBy(Object createBy) {
			this.createBy = createBy;
		}

		public Object getUpdateBy() {
			return updateBy;
		}

		public void setUpdateBy(Object updateBy) {
			this.updateBy = updateBy;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCityname() {
			return cityname;
		}

		public void setCityname(String cityname) {
			this.cityname = cityname;
		}

		public String getCitycode() {
			return citycode;
		}

		public void setCitycode(String citycode) {
			this.citycode = citycode;
		}

		public String getTemp() {
			return temp;
		}

		public void setTemp(String temp) {
			this.temp = temp;
		}

		public String getCurrenttemp() {
			return currenttemp;
		}

		public void setCurrenttemp(String currenttemp) {
			this.currenttemp = currenttemp;
		}

		public String getWeather() {
			return weather;
		}

		public void setWeather(String weather) {
			this.weather = weather;
		}

		public long getWeatherdate() {
			return weatherdate;
		}

		public void setWeatherdate(long weatherdate) {
			this.weatherdate = weatherdate;
		}

		public String getLogourl() {
			return logourl;
		}

		public void setLogourl(String logourl) {
			this.logourl = logourl;
		}

		public Object getWd() {
			return wd;
		}

		public void setWd(Object wd) {
			this.wd = wd;
		}

		public Object getWs() {
			return ws;
		}

		public void setWs(Object ws) {
			this.ws = ws;
		}

		public Object getTenantId() {
			return tenantId;
		}

		public void setTenantId(Object tenantId) {
			this.tenantId = tenantId;
		}
	}
}
