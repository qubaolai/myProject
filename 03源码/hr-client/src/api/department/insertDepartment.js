import intercept from "@/utils/intercepter";

export function insertDept(data) {
  return intercept.request({
    method: "post",
    url: "/department/insertDept",
    data: data
  });
}
