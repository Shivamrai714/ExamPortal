package com.exam.controllers;

import com.exam.models.exam.Question;
import com.exam.models.exam.Quiz;
import com.exam.services.QuestionService;
import com.exam.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController
{

    @Autowired
    private QuestionService questionService;


    @Autowired
    private QuizService quizService;       //Used in display quesion of Quiz , to first load the Quiz then filter the question


    //Add question
    @PostMapping("/")
    public ResponseEntity<Question> add(@RequestBody Question question)
    {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }


    //delete question

    @DeleteMapping("/{quesId}")
    public  void delete(@PathVariable("quesId") Long quesId)
    {
        this.questionService.deleteQuestion(quesId);
    }


    //Update Question  --Need to take care we need to pass the old id , othewise the new data will be added as the same save method is used in implementation
    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody Question question)
    {
      return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }


    // get single question with its id
    @GetMapping("/{quesId}")
    public Question get(@PathVariable("quesId") Long quesId)
    {
        return this.questionService.getQuestion(quesId);
    }




     //USED in Video 40
    // get limited Questions of Specific Quiz from total Question it posseses.
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?>getQuestionsOfQuiz(@PathVariable("qid") Long qid) {

        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Question> questions = quiz.getQuestions();

        List<Question> list = new ArrayList(questions);

        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() ));   //Changed the content here.
        }

        //VIDEO 46 : Basically emptying the answer field .This will remove the ans for normal user from the console.


        list.forEach( (q)->{
            q.setAnswer("");
        });




        Collections.shuffle(list);

        //JUST EMPTY THE ANSWER FIELD OVER HERE ny traversing all the questions .
        System.out.println("Tackling   ...  ");
        System.out.println(list);


        return ResponseEntity.ok(list);
    }
    /*
      // This is going to fetch all the question of That Quiz but we need only no of Question given in current quiz
     //  So idea is to load the quiz first using QuizService and then filter out the Questions

        Quiz quiz1= new Quiz();
        quiz1.setqId(qid);

        Set<Question> questionOfQuiz = this.questionService.getQuestionOfQuiz(quiz1);
         return ResponseEntity.ok(questionOfQuiz);
    */

        // NOW We are designing the fucntion which will be giving the random  Question  with given constraint of "no of Questions"  only to be displayed.
        //Load the Quiz first ....







//CREATED NEW FUNCTION VIDEO 30 : to get all question of Quiz , ie removing the filter

    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?>getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid)
    {
        Quiz quiz1= new Quiz();
        quiz1.setqId(qid);

        Set<Question> questionOfQuiz = this.questionService.getQuestionsOfQuiz(quiz1);
        return ResponseEntity.ok(questionOfQuiz);
    }











    /*
    // get all questions of All subjects ::: Here NO use
    @GetMapping("/")
    public    ResponseEntity<?> getQuestions()
    {
        return ResponseEntity.ok(this.questionService.getQuestions());
    }
*/


    //VIDEO  44 :

    //EVALUTAING QUIZ
    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions)
    {
        System.out.println(questions);

        int maxMarks=0; maxMarks=Integer.parseInt(questions.get(0).getQuiz().getMaxMarks() );
        double marksGot=0;
        int correctAnswers=0;
        int attempted=0;



        // Need to check the answers clicked by user

        for(Question q : questions){
           //  System.out.println(q.getGivenAnswer());

             Question question = this.questionService.get(q.getQuesId());

             if( question.getAnswer().equals(q.getGivenAnswer()))
             {
                 //correct
                 correctAnswers++;
                 double marksSingle= Double.parseDouble(questions.get(0).getQuiz().getMaxMarks() )/questions.size();

                 marksGot+=marksSingle;

             }
             if(q.getGivenAnswer() !=null) {
                 attempted++;
             }



         }

        System.out.println(maxMarks+"   Done");
        Map<String, Object> map = Map.of( "maxMarks",maxMarks ,"marksGot", marksGot, "correctAnswers", correctAnswers, "attempted", attempted);

        return ResponseEntity.ok(map);

    }





}
