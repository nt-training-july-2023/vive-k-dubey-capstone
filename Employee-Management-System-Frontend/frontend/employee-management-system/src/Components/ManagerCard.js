import React, { useEffect ,useState} from "react";
import axios from "axios";
import '../CSS/AllManagersList.css';
export default function ManagerCard({manager}) {

    const [projectList, setProjectList] = useState([{
        "id": 1,
        "projectName": "PetSmart",
        "managerId": "23",
        "startDate": "12/12/2023",
        "skillsRequired": [
            "React",
            "Node.js",
            "Python"
        ],
        "description": "PetSmart is a privately held American chain of pet superstores, which sell pet products, services, and small pets. It is the leading North American pet company, and its direct competitor is Petco. Its indirect competitors are Amazon, Walmart, and Target."
    }]);
    const [selectedProject ,setSelectedProject] = useState("");

    async function apiCall() {
      const res =await axios.get(`http://localhost:8081/employee/getAll/project/${manager.id}`);
    //   console.log("res data",res.data);
      setProjectList(res.data);

    }
    
    useEffect(() =>{
        apiCall();

        // if (projectList.length > 0) {
        //     setSelectedProject(projectList[0].id);
        //   }
    },[])

    console.log("projectlist", projectList);

    function handleChange(event) {
            // console.log("project details", event.target.value);
            setSelectedProject(event.target.value);
        }
    return (
        <div className="card" key={manager.id}>
            <div className="left-section">
              <div className="employee-name larger">{manager.empName}</div>
              <div className="field smaller" style={{fontWeight: 600}}>{manager.empDesignation}</div>
              <div className="field">
              <select
                    name="projectName"
                    id = "projectName"
                    className="select"
                    onChange={handleChange}
                    // value={selectedProject}
                >
                    <option value="">Select Project</option>
                    {projectList.map((project) => {
                    return <option key={project.id} 
                    value={project.id}
                    >{project.projectName}
                    </option>
                    })}
            </select>
            </div>
            {/* <div className="field">
                <select id="projectName">
                  <option value="Actual Project">Project</option>
                  {manager.projectNames.map((projectName, projectIndex) => (
                    <option key={projectIndex} value={projectName}>
                      {projectName}
                    </option>
                  ))}
                </select>
              </div> */}
              <div className="field"><span style={{ fontWeight: 600 }}>Contact:</span> {manager.empContactNo}</div>
              <div className="field" ><span style={{ fontWeight: 600 }}>Email:</span> {manager.empEmail}</div>
              <div className="field"><span style={{ fontWeight: 600 }}>Location:</span> {manager.empLocation}</div>
            </div>
            <div className="right-section-manager">
              <div className="employee-id "><span style={{ fontWeight: 600 }}>Employee ID:</span> {manager.empId}</div>
              
              <div className="field"><span style={{ fontWeight: 600 }}>Skills:</span> 
              {
                projectList.map((project) => {
                console.log(project.id, "===", selectedProject)
                if ((project.id + "") === selectedProject) {
                // Render the skills for the selected project
                // { console.log("Id working", project.skillsRequired) }
                return project.skillsRequired
                    .map((skill, index) => {
                    const isLast = index === project.skillsRequired.length - 1;
                    if (isLast)
                        return skill
                    else
                        return skill + ", "
                    }
                        )
                    }
                    })
                }
              </div>
              <div className="field"><span style={{ fontWeight: 600 }}>Team:</span> Vivek Dubey, Abhay Gupta,s Ashish Sahu</div>
            </div>
          </div>
    )
}