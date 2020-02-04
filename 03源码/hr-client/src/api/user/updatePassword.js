import intercept from "@/utils/intercepter";

export function updatePassword(data) {
  return intercept.request({
    method: "put",
    url: "/user/updatePassword",
    data: data
  });
}
