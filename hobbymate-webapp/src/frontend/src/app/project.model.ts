import {IArtefact} from "./artefact.model";

export interface IProject {
 id?: number;
 userId?: number;
 artefact: IArtefact;
 title?: string;
 isComplete?: boolean;
 shortDescription?: string,
 description?: string;
}
