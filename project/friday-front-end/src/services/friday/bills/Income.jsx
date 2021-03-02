import { stringify } from "qs";
// ant 自己封装好的发送ajax请求的工具
import request from "@/utils/request";

export async function selectAll(params) {
  return request(`/api/friday/bills/userIncome/selectAll?${stringify(params)}`,{
    method:"GET"
  });
}

// export async function queryUser2(params) {
//   return request(`/server/api//test/user?${params}`, {
//     method: "POST"
//   });
// }
