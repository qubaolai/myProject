import intercept from "@/utils/intercepter";

export function updateEmployee(data) {
  return intercept.request({
    method: "put",
    url: "/user/updateEmployee",
    data: data
  });
}
