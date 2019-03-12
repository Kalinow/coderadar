import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Project} from '../model/project';
import {Router} from '@angular/router';
import {UserService} from './user.service';
import {FilePatterns} from '../model/file-patterns';
import {AnalyzerConfiguration} from '../model/analyzer-configuration';
import {Commit} from '../model/commit';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private userService: UserService, private router: Router, private httpClient: HttpClient) { }

  private apiURL = 'http://localhost:8080/';

  /**
   * Gets all the projects from the server.
   * Sends a GET request to /projects
   */
  public getProjects(): Promise<HttpResponse<any>> {
    return this.httpClient.get<any>(this.apiURL + 'projects', {observe: 'response'}).toPromise();
  }

  /**
   * Gets a project from the database.
   * Sends a GET request to /projects/{id}
   * @param id The id of the project.
   */
  public getProject(id: number): Promise<HttpResponse<any>> {
    return this.httpClient.get<any>(this.apiURL + 'projects/' + id , {observe: 'response'}).toPromise();
  }

  /**
   * Adds a new project.
   * Sends a POST request to /projects.
   * @param project The project to add.
   */
  public addProject(project: Project): Promise<HttpResponse<any>> {
    return this.httpClient.post<any>(this.apiURL + 'projects', JSON.stringify(project), {observe: 'response'}).toPromise();
  }

  /**
   * Edits an existing project.
   * Sends a POST request to /projects/{id}
   * @param project A Project object containing the changes.
   */
  public editProject(project: Project): Promise<HttpResponse<any>> {
    return this.httpClient.post<any>(this.apiURL + 'projects/' + project.id, JSON.stringify(project), {observe: 'response'}).toPromise();
  }

  /**
   * Deletes a project from the database (as long as it's not being analyzed at the moment).
   * Sends a DELETE request to /projects/{id}
   * @param id The id of the project to delete.
   */
  public deleteProject(id: number): Promise<HttpResponse<any>> {
    return this.httpClient.delete(this.apiURL + 'projects/' + id, {observe: 'response'}).toPromise();
  }

  /**
   * Gets the file patterns for a project given it's id.
   * Sends a GET request to /projects/{id}/files
   * @param id The id of the project.
   */
  public getProjectFilePatterns(id: number): Promise<HttpResponse<any>> {
    return this.httpClient.get<any>(this.apiURL + 'projects/' + id + '/files', {observe: 'response'}).toPromise();
  }

  /**
   * Sets the file patterns for a project.
   * Sends a POST request to /projects/{id}/files
   * @param id The id of the project.
   * @param patterns an array of FilePatterns objects.
   */
  public setProjectFilePatterns(id: number, patterns: FilePatterns[]): Promise<HttpResponse<any>> {
    return this.httpClient.post(this.apiURL + 'projects/' + id + '/files', {filePatterns: patterns},
      {observe: 'response'}).toPromise();
  }

  /**
   * Gets the modules for a projects given it's id.
   * Sends a GET request to /projects/{id}/modules
   * @param id The id of the  project.
   */
  public getProjectModules(id: number): Promise<HttpResponse<any>> {
    return this.httpClient.get<any>(this.apiURL + 'projects/' + id + '/modules', {observe: 'response'}).toPromise();
  }

  /**
   * Adds a module to a project.
   * Sends a POST request to /projects/{id}/modules
   * @param id The id of the project.
   * @param module The name (path) of the module.
   */
  public addProjectModule(id: number, module: string): Promise<HttpResponse<any>> {
      return this.httpClient.post(this.apiURL + 'projects/' + id + '/modules', {modulePath: module}, {observe: 'response'}).toPromise();
  }

  /**
   * Returns all of the configured analyzer for a project.
   * Sends a GET request to /projects/{id}/analyzers
   * @param id The id of the project.
   */
  public getProjectAnalyzers(id: number): Promise<HttpResponse<any>> {
    return this.httpClient.get<any>(this.apiURL + 'projects/' + id + '/analyzers', {observe: 'response'}).toPromise();
  }

  /**
   * Adds an analyzer configuration to a project.
   * Sends POST request to /projects/{id}/analyzers
   * @param id The id of the project.
   * @param analyzer The analyzer configuration to add.
   */
  public addAnalyzerConfigurationToProject(id: number, analyzer: AnalyzerConfiguration): Promise<HttpResponse<any>> {
    return this.httpClient.post(this.apiURL + 'projects/' + id + '/analyzers', JSON.stringify(analyzer), {observe: 'response'}).toPromise();
  }

  /**
   * Modifies an existing analyzer configuration for a project.
   * Sends a POST request to /projects/{id}/analyzers/{analyzerId}
   * @param id The id of the project.
   * @param analyzer The analyzerConfiguration to modify.
   */
  public editAnalyzerConfigurationForProject(id: number, analyzer: AnalyzerConfiguration): Promise<HttpResponse<any>> {
    return this.httpClient.post(this.apiURL + 'projects/' + id + '/analyzers/' + analyzer.id, JSON.stringify(analyzer),
      {observe: 'response'}).toPromise();
  }

  /**
   * Gets all of the available analyzers in coderadar.
   * Sends a GET request to /analyzers.
   */
  public getAnalyzers(): Promise<HttpResponse<AnalyzerConfiguration[]>> {
    return this.httpClient.get<AnalyzerConfiguration[]>(this.apiURL + 'analyzers', {observe: 'response'}).toPromise();
  }

  /**
   * Gets the running analyzing job for a project.
   * Sends a GET request to /projects/{id}/analyzingJob
   * @param id The project id.
   */
  public getAnalyzingJob(id: number): Promise<HttpResponse<any>> {
    return this.httpClient.get<any>(this.apiURL + 'projects/' + id + '/analyzingJob', {observe: 'response'}).toPromise();
  }

  /**
   * Start a new analyzing for a project.
   * Sends a POST request to /projects/{id}/analyzingJob
   * @param id The id of the project.
   */
  public startAnalyzingJob(id: number): Promise<HttpResponse<any>> {
    return this.httpClient.post(this.apiURL + 'projects/' + id + '/analyzingJob', {fromDate: 0, active: true, rescan: true},
      {observe: 'response'}).toPromise();
  }

  /**
   * Gets all available commits for a project.
   * Sends a GET request to /projects/{id}/commits
   * @param id The project id.
   */
  public getCommits(id: number): Promise<HttpResponse<Commit[]>> {
    return this.httpClient.get<Commit[]>(this.apiURL + 'projects/' + id + '/commits', {observe: 'response'}).toPromise();
  }

  /**
   * Gets the metric values for a commit given it's name and the metric names.
   * Sends a POST request to /projects/{id}/metricvalues/perCommit
   * @param id The project id.
   * @param commitName The name (hash) of the commit.
   * @param metricsNames The names of the wanted metrics.
   */
  public getCommitsMetricValues(id: number, commitName: string, metricsNames: string[]): Promise<HttpResponse<any>> {
    return this.httpClient.post(this.apiURL + 'projects/' + id + '/metricvalues/perCommit',
      {commit: commitName, metrics: metricsNames}, {observe: 'response'}).toPromise();
  }
}
