import intercept from "@/utils/intercepter";

export function schedulingUser(data) {
  return intercept.request({
    method: "put",
    url: "/user/schedulingUser",
    data: data
  });
}
export function getMoveLog(data) {
  return intercept.request({
    method: "post",
    url: "/user/getMoveLog",
    data: data
  });
}
