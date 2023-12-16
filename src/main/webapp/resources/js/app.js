


	// -----------------------------------------------------------------------------------
	// 문제 출력 함수
	function update_quiz(){
		var question = document.getElementById('question');
		var idx = quiz.questionIndex + 1;
		var choice = document.querySelectorAll('.btn');

		// 문제 출력
		question.innerHTML = '문제'+ idx + ') ' + quiz.questions[quiz.questionIndex].text;

		// 선택 항목 출력
		for(var i = 0; i < 4; i++){
			choice[i].innerHTML = quiz.questions[quiz.questionIndex].choice[i];
		}

		progress();
	}


	// 문제 진행 정보 표시(현재 문제 번호/총 문항수)
	function progress(){
		var progress = document.getElementById('progress');
		progress.innerHTML = '문제 ' + (quiz.questionIndex+1) + ' / ' + quiz.questions.length;
	}


	// -----------------------------------------------------------------------------------	
	// 결과 표시
	function result(){
		var quiz_div = document.getElementById('quiz');	// 퀴즈 박스 컨테이너

		// 백분률 점수 계산
		var per = parseInt((quiz.score*100) / quiz.questions.length);

		// 표시할 텍스트 정보와 변수
		var txt =	'<h1>결과</h1>' +
					'<h2 id="score"> 당신의 점수: ' + quiz.score + '/' +  
					quiz.questions.length + '<br><br>' + per + '점</h2>';

		quiz_div.innerHTML = txt;	// 결과 텍스트 출력

		// 점수 별 결과 텍스트 출력
		if(per < 60){
			txt += '<h2 style="color:red">좀더 분발하세요</h2>';
			quiz_div.innerHTML = txt;
		} else if(per >= 60 && per < 80){
			txt += '<h2 style="color:red">무난한 점수네요</h2>';
			quiz_div.innerHTML = txt;
		} else if(per >= 80){
			txt += '<h2 style="color:red">훌륭합니다</h2>'
			quiz_div.innerHTML = txt;
		}
	} // end result


	// -----------------------------------------------------------------------------------
	var btn = document.querySelectorAll('.btn');	// .btn 객체

	// 입력 및 정답 확인 함수
	function checkAnswer(i){
		// 선택버튼(.btn) 이벤트 리스너 
		btn[i].addEventListener('click', function(){
			var answer = btn[i].innerText;

			if(quiz.correctAnswer(answer)){
				alert('정답입니다!');
				quiz.score++;
			} else{ alert('틀렸습니다!'); }

			// 다음 문제로 진행 및 결과 처리
			if(quiz.questionIndex < quiz.questions.length-1){
				quiz.questionIndex++;
				update_quiz();
			} else { 
				// 결과 화면
				result(); 
			}
		});	
	} // end checkAnswer

	// 4개의 버튼 이벤트리스너 지정
	for(var i = 0; i < btn.length; i++){
		checkAnswer(i);
	}

	update_quiz();


