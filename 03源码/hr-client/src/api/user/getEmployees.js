import intercept from "@/utils/intercepter";

export function getEmpList() {
  return intercept.request({
    method: "post",
    url: "/user/employeeList"
  });
}
