/**
 * 数组过滤器 例如：{{SECTION_CHARGE | optionKV(section.charge)}}
 * @param object 例如：{CHARGE:{key:"C", value:"收费"},FREE:{key:"F", value:"免费"}}
 * @param key 例如：C
 * @returns {string} 例如：收费
 */
let optionKV = (object, key) =>  {
  if (!object || !key) {
    return "";
  } else {
    let result = "";
    for(let enums in object){
      console.log(object[enums]["key"]);
      if (key === object[enums]["key"]) {
        result = object[enums]["value"];
      }
    }
    return result;
  }
};
let optionKVArray  = (list,key)=>{
  if(!list || !key){
    return "";
  }else {
    let result = "";
    for (let i = 0; i < list.length; i++) {
      if (key == list[i]["key"]){
        result = list[i]["value"];
      }
    }
    return result;
  }

};
/**
 * 时长格式化
 * @param value 例如：36000
 * @returns {string} 例如：10:00:00
 */
let formatSecond = (value) => {
  value = value || 0;
  let second = parseInt(value, 10); // 秒
  let minute = 0; // 分
  let hour = 0; // 小时
  if (second > 60) {
    // 当大于60秒时，才需要做转换
    minute = Math.floor(second / 60);
    second = Math.floor(second % 60);
    if (minute > 60) {
      hour = Math.floor(minute / 60);
      minute = Math.floor(minute % 60);
    }
  } else {
    // 小于60秒时，直接显示，不需要处理
  }
  let result = "" + PrefixInteger(second, 2) + "";
  // 拼上分钟
  result = "" + PrefixInteger(minute, 2) + ":" + result;
  // 拼上小时
  result = "" + PrefixInteger(hour, 2) + ":" + result;
  return result;
};

/**
 * 格式化文件大小
 * @param value
 * @returns {string}
 */
let formatFileSize = (value) => {
  value = value || 0;
  let result;
  if (value > 100 * 1024) {
    result = Math.round((value / 1024 / 1024) * 100) / 100 + "MB";
  } else {
    result = Math.round((value / 1024) * 100) / 100 + "KB";
  }
  return result;
};

function PrefixInteger(num,length) {
  return (Array(length).join('0')+num).slice(-length);
}
export default  {
  optionKV,
  formatSecond,
  formatFileSize
}