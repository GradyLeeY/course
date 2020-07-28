Tool = {
  /**
   * 空校验null 或者""都返回true
   * @param obj
   * @returns {boolean}
   */
  isEmpty: function (obj) {
    if (typeof obj == 'string'){
      return !obj || obj.replace(/\s+/g, "") == "";
    }else {
      return (!obj || JSON.stringify(obj) === '{}' || obj.length == 0);
    }
  },
  /**
   * 非空校验
   * @param obj
   * @returns {boolean}
   */
  isNotEmpty: function (obj) {
    return !this.isEmpty(obj);
  },
  /**
   * 长度校验
   * @param str
   * @param min
   * @param max
   * @returns {boolean}
   */
  isLength: function (str, min, max) {
    return $.trim(str).length >= min && $.trim(str).length <= max;
  },
  /**
   * 时间格式化
   */
  dateFormat: function (format,date) {
    let result;
    if (!date){
      date = new Date();
    }
    const option = {
      "y+": date.getFullYear().toString(),
      "M+": (date.getMonth() + 1).toString(),
      "d+": date.getDate().toString(),
      "h+": date.getHours().toString(),
      "m+": date.getMinutes().toString(),
      "s+": date.getSeconds().toString()
    }
    for(let i in option){
      result = new RegExp("("+i+")").exec(format);
      if (result){
        format = format.replace(result[1], (result[1].length == 1) ? (option[i]) : (option[i].padStart(result[1].length, "0")))
      }
    }
    return format;
  },

  removeObj: function (array,obj) {
    let index = -1;
    for (let i = 0; i < array.length; i++) {
      if (array[i] === obj){
        array.splice(i,1);
        index = i;
        break;
      }
    }
    return index;
  },

  //十进制转62进制
  _10to62: function (number) {
    let chars = "0123456789abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";
    let radix = chars.length;
    let arr = [];
    do {
      let mod = number%radix;
      number = (number - mod)%radix;
      arr.unshift(chars[mod]);
    }while (number);
    return arr.join('');
  },
  /**
   * 保存登录用户信息
   */
  setLoginUser: function (loginUser) {
    SessionStorage.set(SESSION_KEY_LOGIN_USER, loginUser);
  },

  /**
   * 获取登录用户信息
   */
  getLoginUser: function () {
    return SessionStorage.get(SESSION_KEY_LOGIN_USER) || {};
  },

  /**
   * 随机生成[len]长度的[radix]进制数
   * @param len
   * @param radix 默认62
   * @returns {string}
   */
  uuid: function (len, radix) {
    let chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    let uuid = [];
    radix = radix || chars.length;

    for (let i = 0; i < len; i++) {
      uuid[i] = chars[0 | Math.random() * radix];
    }

    return uuid.join('');
  }
}