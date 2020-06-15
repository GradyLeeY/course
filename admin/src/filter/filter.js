
let optionKv = (list,key)=>{
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
  optionKv
}