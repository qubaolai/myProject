import intercept from "@/utils/intercepter";

export function deleteDept(data) {
  return intercept.request({
    method: "get",
    url: "/department/deleteDepartment",
    params: { id: data }
  });
}
