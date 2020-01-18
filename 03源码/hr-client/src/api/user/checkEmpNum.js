import intercept from "@/utils/intercepter";

export function checkEmpNum(data) {
  return intercept.request({
    method: "get",
    url: "/user/checkEmployeeNumber",
    params: { empNum: data }
  });
}
