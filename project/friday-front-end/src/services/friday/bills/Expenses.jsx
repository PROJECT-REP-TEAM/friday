import { stringify } from "qs";
// ant 自己封装好的发送ajax请求的工具
import request from "@/utils/request";

export async function selectExpenses(params) {
  return request(`/api/friday/bills/userExpenses/selectAll?${stringify(params)}`,{
    method:"GET"
  });
}

export async function updateExpenses(params) {
  return request(`/api/friday/bills/userExpenses/update`, {
    method: "POST",
    headers: {
      'content-type': 'application/json'
    },
    body:JSON.stringify(params)
  });
}

export async function insertExpenses(params) {
  return request(`/api/friday/bills/userExpenses/insert`, {
    method: "POST",
    contentType:'application/json',
    body:JSON.stringify(params)
  });
}


export async function deleteExpenses(params) {
  return request(`/api/friday/bills/userExpenses/delete?${stringify(params)}`, {
    method: "GET"
  });
}
// http://localhost:10010/friday/bills/userExpenses/selectAll
