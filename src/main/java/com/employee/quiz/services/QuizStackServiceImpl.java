package com.employee.quiz.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.hibernate.dialect.DB2390Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.employee.quiz.domain.QuestionPaper;
import com.employee.quiz.domain.Quiz;
import com.employee.quiz.domain.Techstack;
import com.employee.quiz.domain.User;
import com.employee.quiz.repositories.CustomizedRepository;
import com.employee.quiz.repositories.QuestionPaperRepository;
import com.employee.quiz.repositories.QuizRepository;
import com.employee.quiz.repositories.TechStackRepository;
import com.employee.quiz.repositories.UserRepository;
import com.employee.quiz.util.UserProfile;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.core.sym.Name;

/**
 * Created by Abhinav on 19/1/2023.
 */
@Service
public class QuizStackServiceImpl implements QuizStackService {

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private QuestionPaperRepository questionPaperRepository;
	@Autowired
	ExcelHelper excelHelper;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TechStackRepository techStackRepository;

	private CustomizedRepository customizedRepository;

	@Autowired
	public QuizStackServiceImpl(CustomizedRepository customizedRepository) {
		this.customizedRepository = customizedRepository;
	}

	@Override
	public List<Quiz> getAllQuizByTechStack(String techStackId) {
		return (List<Quiz>) customizedRepository.getQuizByTechId(techStackId);
	}

	@Override
	public List<Quiz> getAllMandatoryQuiz() {
		return (List<Quiz>) customizedRepository.getAllMandatoryQuiz();
	}

	@Override
	public Quiz createQuiz(Quiz quiz) {
		return quizRepository.save(quiz);
	}

	@Override
	public Map<String, Object> uploadExelFileToQuiz(MultipartFile file,  int hubbleId) {
		Map<String, Object> response = new HashMap();
		String name;
		try {
//		 List<Quiz> quiz = ExcelHelper.readExcelSheet(file.getInputStream());
//		 quizRepository.saveAll(quiz);
//		 response.put("message", "File uploaded successfully");
//		Path tempDir = Files.createTempDirectory("");
//
//		File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();		
//			file.transferTo(tempFile);
			if (!file.isEmpty()) {
//				Workbook workbook = WorkbookFactory.create(tempFile);
//
//				Sheet sheet = workbook.getSheetAt(0);
				XSSFWorkbook myWorkBook = new XSSFWorkbook(file.getInputStream());
				Integer noOfSheets = myWorkBook.getNumberOfSheets();
				for (int n = 0; n < noOfSheets; n++) {
					XSSFSheet locationDetailsSheet = myWorkBook.getSheetAt(n);
					ArrayList<ArrayList> locationData = excelHelper.readExcelSheet(locationDetailsSheet);
					System.out.println("data: " + locationData);
					int accTotalRows = locationData.size();
//				ArrayList<?> accRowVector = null;
//				for (int i = 0; i < accTotalRows; i++) {
//					accRowVector = (ArrayList) locationData.get(i);
//					
//					if (i != 0) {
//						Map<String, Object> dataMap = new HashMap<>();
//						dataMap.put("quiz_id", accRowVector.get(0));
//						dataMap.put("created_by", accRowVector.get(1));
//						dataMap.put("creation_date", accRowVector.get(2));
//						dataMap.put("last_modified_by", accRowVector.get(3));
//						dataMap.put("last_modified_date", accRowVector.get(4));
//						dataMap.put("quiz_created_date", accRowVector.get(5));
//						dataMap.put("quiz_expired_date", accRowVector.get(6));
//						dataMap.put("is_mandatory", accRowVector.get(7));
//						dataMap.put("quiz_name", accRowVector.get(8));
//						System.out.println(dataMap);
//						System.out.println(dataMap.get("created_by"));
					Quiz quiz = new Quiz();
					QuestionPaper questionPaper = new QuestionPaper();
					Techstack techstack = new Techstack();
					ArrayList<XSSFCell> quizArrayList = new ArrayList<>();
					for (int i = 1; i < accTotalRows; i++) {
						quizArrayList = locationData.get(i);
						// quiz.setQuizId( quizArrayList.get(0).getStringCellValue());
						System.out.println("next :" + quizArrayList.get(0).getStringCellValue());
						if (quizArrayList.get(0).getStringCellValue() == null) {
							break;
						}
						
						name=userRepository.findIdByName(hubbleId);
						questionPaper.setQuestion(quizArrayList.get(0).getStringCellValue());
						questionPaper.setChoice1(quizArrayList.get(1).getStringCellValue());
						questionPaper.setChoice2(quizArrayList.get(2).getStringCellValue());
						questionPaper.setChoice3(quizArrayList.get(3).getStringCellValue());
						questionPaper.setChoice4(quizArrayList.get(4).getStringCellValue());
						questionPaper.setChoice5(quizArrayList.get(5).getStringCellValue());
						questionPaper.setCorrect_answer(quizArrayList.get(6).getStringCellValue());
						questionPaper.setMarkedOption(quizArrayList.get(7).getStringCellValue());
                        questionPaper.setCreatedBy(name);
                        questionPaper.setLastModifiedBy(name);
                        questionPaper.setCreationDate(java.time.LocalDate.now());
                        questionPaper.setLastModifiedDate(java.time.LocalDate.now());
//						based on Name getName Id fromId DB
//						then form techstack oject
//						set to quiz 
//						then save
						// techstack.setTechStackId(UUID.randomUUID().toString());
//				        techstack.setTechStackName(quizArrayList.get(11).getStringCellValue());
//				        
//						quiz.setTechstack(techstack);
//						System.out.println(dataMap.get("created_by"));
//						quiz.setCreatedBy((String) dataMap.get("created_by"));
//						quiz.setCreationDate( (LocalDate) dataMap.get("creation_date"));
//						quiz.setLastModifiedBy(dataMap.get("last_modified_by").toString());
//						quiz.setLastModifiedDate((LocalDate) dataMap.get("last_modified_date"));
//						quiz.setQuizCreatedDate((LocalDate) dataMap.get("quiz_created_date"));
//						quiz.setQuizExpiredDate((LocalDate) dataMap.get("quiz_expired_date"));
//						quiz.setMandatory((boolean) dataMap.get("is_mandatory"));
//						quiz.setQuizName(dataMap.get("quiz_name").toString());
						System.out.println("hello:" + quizArrayList.get(8).getStringCellValue());
						System.out.println(
								"val:" + quizRepository.findIdByName(quizArrayList.get(8).getStringCellValue()));
						quiz.setQuizId(quizRepository.findIdByName(quizArrayList.get(8).getStringCellValue()));
				    questionPaper.setQuiz(quiz);
//					    if(flag==true) {
//					    	quiz.setOpenQuiz(flag);
//					    }
//					    else {
//							quiz.setOpenQuiz(flag);
//						}
//					    if(keyValue==true) {
//					    	quiz.setUnAssignedQuiz(keyValue);
//					    }
//					    else {
//							quiz.setUnAssignedQuiz(keyValue);
//						}
//						quizRepository.save(quiz);
                        questionPaperRepository.save(questionPaper);
						response.put("message", "uploaded excel data to database ");
					}

				}
			}

//				}
			/*
			 * HashMap<String, Object> productMap = new HashMap<String, Object>();
			 * 
			 * List<Object> objectList = new ArrayList<Object>(locationData);
			 * //quizRepository.saveAll(objectList); //long quizId=(long) objectList.get(0);
			 * //System.out.println(quizId); // System.out.println(objectList); //
			 * System.out.println(objectList); // for(int i=0;i<objectList.size();i++){ //
			 * System.out.println(objectList.get(1)); //
			 * //quizRepository.saveAll(objectList.get(1)); // } List<Object> list =
			 * List.of(objectList); System.out.println(objectList); Map<String, Object> map
			 * = new HashMap<>(); for (int i = 0; i < list.size(); i++) {
			 * map.put(list.get(i).toString(), list.get(i)); } System.out.println(map);
			 * System.out.println(map.get("20.0"));
			 */
//			}
		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
		return response;
	}

}
