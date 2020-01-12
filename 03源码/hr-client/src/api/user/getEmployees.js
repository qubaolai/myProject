import intercept from "@/utils/intercepter";

export function getEmpList(data) {
  return intercept.request({
    method: "post",
    url: "/user/employeeList",
    data: data
  });
}
