import intercept from "@/utils/intercepter";

export function insertEmp(data) {
  return intercept.request({
    method: "post",
    url: "/user/insertEmp",
    data: data
  });
}
