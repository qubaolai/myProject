import intercept from "@/utils/intercepter";

export function getAttendance(data) {
  return intercept.request({
    method: "post",
    url: "/attendance/getAttendance",
    data: data
  });
}
