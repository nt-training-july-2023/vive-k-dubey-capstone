export const BASE_URL = 'http://localhost:8081/';

// URLs related to admin controller and register controller.
export const ADD_ADMIN = 'admin';
export const LOGIN = 'login';
export const ALL_EMPLOYEE = 'employee/getAllEmployees';
export const ALL_ORGANIZATION_EMPLOYEE = 'employee/getAllEmployeesAndManagers';
export const ADD_EMPLOYEE = 'employee/add';
export const ALL_MANAGER = 'employee/getAllManagers';
export const ALL_MANAGER_INFO_PROJECT = 'employee/getAllManagersInfo';
export const ASSIGN_PROJECT = 'employee/assignProject';
export const GET_ALL_FILTERED_EMPLOYEE = 'employee/filter';
export const UNASSIGN_PROJECT = 'employee/unassignProject/';
export const ADD_PROJECT = 'employee/addProject';
export const GET_ALL_PROJECT = 'employee/getAllProjects';
export const GET_ALL_PROJECT_FOR_ASSIGN = 'employee/getAllProjectsForAssign';
export const GET_ALL_PROJECT_BY_MANAGER_ID = 'employee/getAll/project/';



//URLs related to request resource.
export const CREATE_REQUEST = 'requestResource/create';
export const IS_REQUESTED_STATUS = 'requestResource/isRequested';
export const GET_ALL_PROJECT_BY_MANAGER_EMAIL = 'getAll/project/byManager/';
export const GET_ALL_REQUESTS = 'requestResource/getAll/requests';
export const REJECT_REQUEST = 'requestResource/reject/';
export const ACCEPT_REQUEST = 'request/accept/';

//URLs related to employee controller.
export const GET_USER_DETAILS = 'api/employee/';
export const UPDATE_SKILLS_OF_USER = 'api/employee/updateskills';