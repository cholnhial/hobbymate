import {IArtefact} from "./artefact.model";
import {ICollaborator} from "./collaborator.model";

export interface IProject {
 id?: number;
 userId?: number;
 artefact: IArtefact;
 collaborators: Array<ICollaborator>,
 title?: string;
 isComplete?: boolean;
 shortDescription?: string,
 description?: string;
}
