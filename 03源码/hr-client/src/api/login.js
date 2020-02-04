import intercept from "@/utils/intercepter";
/**
 * 登录
 */
export function login(formData) {
  return intercept.request({
    method: "post",
    url: "/user/login",
    data: {
      username: formData.username,
      password: formData.password
    },
    headers: {
      "Content-Type": "application/json"
    }
  });
}
