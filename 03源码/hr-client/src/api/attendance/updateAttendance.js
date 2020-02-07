import intercept from "@/utils/intercepter";

export function updateAttendance(data) {
  return intercept.request({
    method: "put",
    url: "/attendance/updateWorkSingIn",
    data: data
  });
}
