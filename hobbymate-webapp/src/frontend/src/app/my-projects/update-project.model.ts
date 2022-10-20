import {IUpdateArtefact} from "./update-artefact.model";

export interface IUpdateProject {
  id?: number;
  artefact?: any;
  title?:string;
  isCompleted?: boolean;
  shortDescription?: string;
  description?: string;
}
