import intercept from "@/utils/intercepter";

export function logout() {
  return intercept.request({
    method: "put",
    url: "/user/logout"
  });
}
