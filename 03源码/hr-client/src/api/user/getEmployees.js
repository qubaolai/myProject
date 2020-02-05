import intercept from "@/utils/intercepter";

export function getEmpList(data) {
  return intercept.request({
    method: "post",
    url: "/user/employeeList",
    data: data
  });
}
export function getEmps() {
  return intercept.request({
    method: "get",
    url: "/user/getEmpList"
  });
}
export function getEmp(data) {
  return intercept.request({
    method: "get",
    url: "/user/getEmployee",
    params: {
      id: data
    }
  });
}
