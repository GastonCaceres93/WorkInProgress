// spec.js
/*describe('Protractor Demo App', function() {
 it('should have a title', function() {
 browser.get('http://juliemr.github.io/protractor-demo/');

 expect(browser.getTitle()).toEqual('Super Calculator');
 });
 });
 */
/*
 describe('Protractor Demo App', function() {
 it('should add one and two', function() {
 browser.get('http://juliemr.github.io/protractor-demo/');
 element(by.model('first')).sendKeys(1);
 element(by.model('second')).sendKeys(2);

 element(by.id('gobutton')).click();

 expect(element(by.binding('latest')).getText()).
 toEqual('5'); // This is wrong!
 });
 });
 */
describe('gastos compartidos', function() {
	it('login', function() {
		browser.ignoreSynchronization = true;
		browser.get('http://10.210.9.166/SpringMVC4Client/login');
		 element(by.id('username')).sendKeys('sam@xyz.com');
		 element(by.id('password')).sendKeys('abc125');
		 element(by.id('submitBtn')).click();
		 
		 browser.get('#/expenses');
		 
		 var elem = by.css('.ng-binding.ng-scope');
		 var content;
		 browser.driver.isElementPresent(elem).then(function(isPresent){
			 element(elem).getText().then(function(text){
				 content = text;
				 console.log(text);
			 })
		 })
	});
	
	
//	 describe("type in a message ", function(){
//	        it("should find and type in a random message", function(){
//	            var elementToFind = by.css('form textarea.limited');
//	            browser.driver.isElementPresent(elementToFind).then(function(isPresent){
//	                element(elementToFind).sendKeys(randomSentence).then(function(){
//	                    console.log("typed in random message");
//	                    continueOn();
//	                });
//	            });
//	        });
//	    },15000);
	
//	it('gastos',function(){
//		browser.ignoreSynchronization = false;
//		element(by.linkText('Gastos')).click();
//	});

});