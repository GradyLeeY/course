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
export default  {
  optionKV
}