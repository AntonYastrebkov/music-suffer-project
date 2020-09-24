const compose = (...fcn) => (component) => {
  return fcn.reduceRight((prevRes, f) => f(prevRes), component);
};

export default compose;