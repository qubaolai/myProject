import intercept from "@/utils/intercepter";

export function upload(formData) {
  return intercept.request({
    method: "post",
    url: "/file/login",
    data: {
      username: formData.username,
      password: formData.password
    },
    headers: {
      "Content-Type": "application/json"
    }
  });
}
