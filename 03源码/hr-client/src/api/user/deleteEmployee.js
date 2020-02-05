import intercept from "@/utils/intercepter";

export function deleteEmployee(data) {
  return intercept.request({
    method: "delete",
    url: "/user/deleteEmployee",
    params: {
      id: data
    }
  });
}
